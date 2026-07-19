#!/usr/bin/env python3
"""
run-tests.py - Flexible test runner for CaffC tests with configurable profiles.

Profiles define the commands and flags for caffc compilation, C compilation,
linking, and execution. Settings are hierarchical: defaults -> profile ->
per-project overrides.

Usage:
    python3 run-tests.py [options] [TEST_NAMES...]

Examples:
    # Run all tests with default profile
    python3 run-tests.py

    # Run with a specific profile
    python3 run-tests.py --profile gcc

    # Run individual tests with profile
    python3 run-tests.py --profile gcc hello-world-caffc string-tests

    # Override flags for a single run
    python3 run-tests.py --compile-flags "-Wall -O2" --link-flags "-lm" hello-world-caffc

    # Custom caffc command
    python3 run-tests.py --caffc-cmd "java -jar /path/to/caffc.jar"

Profiles are loaded from profiles/ directory (YAML files).
Per-project overrides go in <test_dir>/run-tests-config.yaml.
"""

import argparse
import concurrent.futures
import glob
import os
import shutil
import subprocess
import sys
import time
from dataclasses import dataclass, field
from enum import Enum
from pathlib import Path
from typing import Any, Dict, List, Optional, Tuple


# ---------------------------------------------------------------------------
# YAML loader (no external deps – uses a minimal subset parser)
# ---------------------------------------------------------------------------

def load_yaml(path: str) -> dict:
    """Minimal YAML loader for flat/nested key-value structures.

    Supports:
    - string, int, float, bool, null values
    - nested mappings (dicts)
    - sequences (lists)
    - comments (#)
    - quoted strings
    Does NOT support multi-line strings, anchors, or complex types.
    """
    with open(path, "r") as f:
        lines = f.readlines()
    return _parse_lines(lines, 0, 0)[0]


def _skip_blanks_and_comments(lines: list, idx: int) -> int:
    while idx < len(lines):
        stripped = lines[idx].lstrip()
        if stripped and not stripped.startswith("#"):
            break
        idx += 1
    return idx


def _get_indent(line: str) -> int:
    return len(line) - len(line.lstrip())


def _parse_value(val_str: str) -> Any:
    val_str = val_str.strip().rstrip(",")
    if val_str == "" or val_str == "~" or val_str.lower() == "null":
        return None
    if val_str.lower() == "true":
        return True
    if val_str.lower() == "false":
        return False
    # Quoted string
    if (val_str.startswith('"') and val_str.endswith('"')) or \
       (val_str.startswith("'") and val_str.endswith("'")):
        return val_str[1:-1]
    # Number
    try:
        return int(val_str)
    except ValueError:
        pass
    try:
        return float(val_str)
    except ValueError:
        pass
    return val_str


def _parse_lines(lines: list, start: int, base_indent: int) -> tuple:
    result: dict = {}
    idx = start

    while idx < len(lines):
        # Skip blanks and comments before checking indent
        idx = _skip_blanks_and_comments(lines, idx)
        if idx >= len(lines):
            break

        line = lines[idx]
        indent = _get_indent(line)

        if indent < base_indent:
            break
        if indent > base_indent:
            break

        stripped = line.strip()
        if not stripped or stripped.startswith("#"):
            idx += 1
            continue

        # Check if this is a list item
        if stripped.startswith("- "):
            # This is a sequence – re-parse as list
            result_list, idx = _parse_list(lines, idx, base_indent)
            # Since our top-level expects dicts, this shouldn't happen here
            break

        # Key-value pair
        if ":" in stripped:
            colon_pos = stripped.index(":")
            key = stripped[:colon_pos].strip()
            rest = stripped[colon_pos + 1:].strip()

            if rest in (">", "|"):
                # Block scalar (folded or literal multi-line string)
                idx += 1
                block_lines = []
                # Determine the indent of the next non-empty line
                peek = _skip_blanks_and_comments(lines, idx)
                block_indent = None
                if peek < len(lines):
                    block_indent = _get_indent(lines[peek])
                while idx < len(lines):
                    if not lines[idx].strip():
                        idx += 1
                        continue
                    line_indent = _get_indent(lines[idx])
                    if block_indent is not None and line_indent < block_indent:
                        break
                    block_lines.append(lines[idx].rstrip())
                    idx += 1
                # Folded: replace newlines with spaces; Literal: keep newlines
                sep = "\n" if rest == "|" else " "
                result[key] = sep.join(block_lines).strip()
            elif rest:
                # Inline value
                result[key] = _parse_value(rest)
                idx += 1
            else:
                # Nested structure
                idx += 1
                next_indent = _skip_blanks_and_comments(lines, idx)
                if next_indent < len(lines):
                    child_indent = _get_indent(lines[next_indent])
                    if child_indent > indent:
                        child_content, idx = _parse_lines(lines, next_indent, child_indent)
                        if child_content is not None and isinstance(child_content, dict):
                            # Check if it's actually a list
                            first_key = list(child_content.keys())[0] if child_content else None
                            if first_key == "_LIST_MARKER_":
                                result[key] = child_content["_LIST_MARKER_"]
                            else:
                                result[key] = child_content
                        else:
                            result[key] = child_content
                else:
                    result[key] = None
        else:
            idx += 1

    return result, idx


def _parse_list(lines: list, start: int, base_indent: int) -> tuple:
    result: list = []
    idx = start

    while idx < len(lines):
        stripped = lines[idx].lstrip()
        indent = _get_indent(lines[idx])

        if indent < base_indent:
            break

        if stripped.startswith("- "):
            item_content = stripped[2:].strip()
            if ":" in item_content:
                # List of dicts – parse as inline mapping
                # Gather all continuation lines at deeper indent
                item_dict = {}
                colon_pos = item_content.index(":")
                key = item_content[:colon_pos].strip()
                val = item_content[colon_pos + 1:].strip()
                if val:
                    item_dict[key] = _parse_value(val)
                else:
                    item_dict[key] = None
                idx += 1
                item_indent = indent + 2  # expected indent for keys inside list item
                while idx < len(lines):
                    next_stripped = lines[idx].lstrip()
                    if not next_stripped or next_stripped.startswith("#"):
                        idx += 1
                        continue
                    next_indent = _get_indent(lines[idx])
                    if next_indent < item_indent:
                        break
                    if ":" in next_stripped:
                        cp = next_stripped.index(":")
                        k = next_stripped[:cp].strip()
                        v = next_stripped[cp + 1:].strip()
                        if v:
                            item_dict[k] = _parse_value(v)
                        else:
                            item_dict[k] = None
                    idx += 1
                result.append(item_dict)
            else:
                result.append(_parse_value(item_content))
                idx += 1
        elif not stripped or stripped.startswith("#"):
            idx += 1
        else:
            break

    return result, idx


# ---------------------------------------------------------------------------
# Data structures
# ---------------------------------------------------------------------------

class TestResult(Enum):
    PASSED = "passed"
    FAILED = "failed"
    ERROR = "error"


@dataclass
class TestConfig:
    """Configuration for a single test run."""
    caffc_cmd: str = ""
    caffc_flags: str = ""
    compile_cmd: str = ""
    compile_flags: str = ""
    link_cmd: str = ""
    link_flags: str = ""
    execute_cmd: str = ""
    execute_extra_args: str = ""
    extra_c_sources: str = ""
    extra_libs: str = ""
    caffc_onefile: str = ""
    # Template fields: if set, used instead of compile_cmd/link_cmd/execute_cmd
    # Placeholders: {file} = source path, {output} = object path
    #   link: {objects} = space-separated .o files, {output} = binary path
    #   execute: {binary} = binary path
    compile_cmd_template: str = ""
    link_cmd_template: str = ""
    execute_cmd_template: str = ""
    # Docker shared-container fields
    # When docker_run_command is set, a single container is started once for
    # all tests in a profile, and compile/link/execute run via docker exec.
    # Placeholders: {container_name}, {mount_source}, {cwd}, {user}
    # docker_exec_prefix is prepended, docker_exec_suffix is appended to the
    # quoted command string (e.g. 'username' for su -c '...' username)
    docker_container_name: str = ""
    docker_mount_source: str = ""
    docker_run_command: str = ""
    docker_exec_prefix: str = ""
    docker_exec_suffix: str = ""
    docker_stop_command: str = ""
    # Optional: command to poll for container readiness (replaces default `true`)
    # If set, the container is considered ready when this command succeeds.
    docker_ready_command: str = ""


@dataclass
class Profile:
    """A named profile containing default settings."""
    name: str
    config: TestConfig = field(default_factory=TestConfig)


@dataclass
class TestResultSummary:
    """Result of a single test."""
    test_name: str
    result: TestResult
    duration_sec: float
    message: str = ""


# ---------------------------------------------------------------------------
# Docker shared-container lifecycle
# ---------------------------------------------------------------------------

def _host_user() -> str:
    """Get the current host username."""
    try:
        import getpass
        return getpass.getuser()
    except Exception:
        return "root"


class DockerContainer:
    """Context manager that starts a single Docker container once, and
    runs compile/link/execute steps via docker exec.

    Usage:
        with DockerContainer(cfg, profiles_dir) as docker:
            docker_cwd = docker.resolve_cwd(test_dir)
            run_step("cc", cmd, test_dir, docker_cwd=docker_cwd)
    """

    def __init__(self, cfg: TestConfig, profiles_dir: str):
        self.cfg = cfg
        self.profiles_dir = profiles_dir
        self.mount_path: str = ""
        self.is_docker: bool = bool(cfg.docker_run_command)

    def __enter__(self):
        if not self.is_docker:
            return self

        # Resolve mount path (relative to profiles dir, which is inside caffc-tests/)
        mount_source = self.cfg.docker_mount_source
        self.mount_path = os.path.normpath(os.path.abspath(os.path.join(self.profiles_dir, mount_source)))

        # Build the run command
        run_cmd = self.cfg.docker_run_command.replace("{container_name}", self.cfg.docker_container_name)
        run_cmd = run_cmd.replace("{mount_source}", self.mount_path)

        # Remove any stale container with the same name
        subprocess.run(
            f"docker rm -f {self.cfg.docker_container_name}",
            shell=True, capture_output=True)

        print(f"  [docker] Starting container '{self.cfg.docker_container_name}'...", flush=True)
        print(f"  [docker] Mount: {self.mount_path}", flush=True)
        result = subprocess.run(run_cmd, shell=True, capture_output=True, timeout=120)
        if result.returncode != 0:
            stderr = result.stderr.decode("utf-8", errors="replace") if result.stderr else ""
            print(f"  [docker] ERROR: Failed to start container (rc={result.returncode})\n{stderr}",
                  file=sys.stderr)
            sys.exit(1)
        # Wait for container to be ready - poll until docker exec returns success
        ready_cmd = self.cfg.docker_ready_command or "true"
        for attempt in range(60):
            time.sleep(2)
            probe = subprocess.run(
                f"docker exec {self.cfg.docker_container_name} {ready_cmd}",
                shell=True, capture_output=True)
            if probe.returncode == 0:
                break
        else:
            print("  [docker] ERROR: Container did not become ready in 120s", file=sys.stderr)
            sys.exit(1)
        print(f"  [docker] Container started.", flush=True)
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        if not self.is_docker:
            return False

        stop_cmd = self.cfg.docker_stop_command or "docker stop {container_name} && docker rm -f {container_name}"
        stop_cmd = stop_cmd.replace("{container_name}", self.cfg.docker_container_name)

        print(f"  [docker] Stopping container '{self.cfg.docker_container_name}'...", flush=True)
        subprocess.run(stop_cmd, shell=True, capture_output=True, timeout=30)
        print(f"  [docker] Container stopped.", flush=True)
        return False

    def resolve_cwd(self, host_cwd: str) -> str:
        """Resolve the host directory path to the container's mounted path."""
        if not self.is_docker:
            return ""
        host_cwd_abs = os.path.abspath(host_cwd)
        if host_cwd_abs.startswith(self.mount_path):
            suffix = host_cwd_abs[len(self.mount_path):]
            if suffix.startswith(os.sep):
                suffix = suffix[len(os.sep):]
            return self.mount_path + ("/" + suffix if suffix else "")
        return host_cwd_abs

    def build_docker_exec_cmd(self, inner_cmd: str, test_dir: str) -> str:
        """Wrap a command with docker exec prefix/suffix, resolving placeholders."""
        docker_cwd = self.resolve_cwd(test_dir)
        prefix = self.cfg.docker_exec_prefix.replace("{container_name}", self.cfg.docker_container_name)
        prefix = prefix.replace("{cwd}", docker_cwd)
        prefix = prefix.replace("{user}", _host_user())
        suffix = self.cfg.docker_exec_suffix.replace("{user}", _host_user())
        if suffix:
            return f"{prefix} '{inner_cmd}' {suffix}"
        return f"{prefix} '{inner_cmd}'"


# ---------------------------------------------------------------------------
# Profile loading
# ---------------------------------------------------------------------------

BUILTIN_PROFILES = {
    "gcc": Profile(
        name="gcc",
        config=TestConfig(
            caffc_cmd="java -jar ../../target/caffc-*.jar",
            compile_cmd="gcc",
            compile_flags="-Wall -g -ggdb",
            link_cmd="gcc",
            link_flags="-g",
        )
    ),
}


def load_profiles_from_dir(profiles_dir: str) -> Dict[str, Profile]:
    """Load profile YAML files from the profiles/ directory."""
    profiles = dict(BUILTIN_PROFILES)
    profile_path = Path(profiles_dir)
    if not profile_path.exists():
        return profiles
    for yaml_file in sorted(profile_path.glob("*.yaml")):
        try:
            data = load_yaml(str(yaml_file))
            profile_name = yaml_file.stem
            config = _config_from_data(data.get("config", {}))
            profiles[profile_name] = Profile(name=profile_name, config=config)
        except Exception as e:
            print(f"WARNING: Failed to load profile '{profile_name}' from {yaml_file}: {e}",
                  file=sys.stderr)
    return profiles


def load_profile_from_file(file_path: str) -> Profile:
    """Load a single profile from a YAML file path."""
    try:
        data = load_yaml(file_path)
        # Use the filename stem as the profile name
        profile_name = Path(file_path).stem
        config = _config_from_data(data.get("config", {}))
        return Profile(name=profile_name, config=config)
    except Exception as e:
        print(f"ERROR: Failed to load profile from '{file_path}': {e}", file=sys.stderr)
        sys.exit(1)


def _config_from_data(data: dict) -> TestConfig:
    """Convert a dict of config data to a TestConfig."""
    cfg = TestConfig()
    all_attrs = {"caffc_cmd", "caffc_flags", "compile_cmd", "compile_flags",
                 "link_cmd", "link_flags", "execute_cmd", "execute_extra_args",
                 "extra_c_sources", "extra_libs", "caffc_onefile",
                 "compile_cmd_template", "link_cmd_template", "execute_cmd_template",
                 "docker_container_name", "docker_mount_source", "docker_run_command",
                 "docker_exec_prefix", "docker_stop_command"}
    for key, val in data.items():
        if hasattr(cfg, key) and val is not None:
            if key in all_attrs:
                if isinstance(val, list):
                    setattr(cfg, key, " ".join(str(v) for v in val))
                else:
                    setattr(cfg, key, str(val))
            else:
                setattr(cfg, key, val)
    return cfg


def load_project_config(test_dir: str) -> Optional[TestConfig]:
    """Load per-project override from <test_dir>/run-tests-config.yaml."""
    config_path = Path(test_dir) / "run-tests-config.yaml"
    if not config_path.exists():
        return None
    try:
        data = load_yaml(str(config_path))
        return _config_from_data(data.get("config", data))
    except Exception as e:
        print(f"WARNING: Failed to load project config from {config_path}: {e}",
              file=sys.stderr)
        return None


# ---------------------------------------------------------------------------
# Merging & resolution
# ---------------------------------------------------------------------------

def merge_configs(defaults: TestConfig, profile: Profile, project: Optional[TestConfig]) -> TestConfig:
    """Merge configs: defaults <- profile <- project overrides.

    For string fields, the non-empty value wins in priority order.
    """
    result = TestConfig()
    for attr in vars(TestConfig()):
        vals = [
            getattr(defaults, attr),
            getattr(profile.config, attr),
            getattr(project, attr) if project else None,
        ]
        # Take the last non-empty, non-None value
        final = None
        for v in vals:
            if v is None:
                continue
            if isinstance(v, TestConfig):
                continue
            if isinstance(v, str) and v.strip() == "":
                continue
            final = v
        if final is None:
            final = getattr(defaults, attr)
        setattr(result, attr, final)
    return result


# ---------------------------------------------------------------------------
# Command building & execution
# ---------------------------------------------------------------------------

def resolve_glob_pattern(pattern: str) -> List[str]:
    """Resolve a glob pattern (e.g. ../../target/caffc-*.jar) to file paths."""
    return sorted(glob.glob(pattern))


def run_step(label: str, cmd_parts: List[str], cwd: str, capture_output: bool = False,
             docker_ctx: Optional[DockerContainer] = None) -> subprocess.CompletedProcess:
    """Run a command and print the command being executed.

    If the command contains shell variables (e.g. $(pwd), $(id -u)),
    it is executed via shell=True.

    When a template is used (single string in cmd_parts), the command
    is always executed via shell.

    If docker_ctx is provided, the command is wrapped with docker exec
    instead of being run locally.
    """
    # Docker mode: wrap with docker exec
    if docker_ctx and docker_ctx.is_docker:
        inner_cmd = " ".join(cmd_parts) if cmd_parts else ""
        docker_cmd = docker_ctx.build_docker_exec_cmd(inner_cmd, cwd)
        print(f"  {label}: {docker_cmd}", flush=True)
        try:
            result = subprocess.run(docker_cmd, shell=True, capture_output=True, timeout=120)
            if result.returncode != 0 and result.stderr:
                stderr_text = result.stderr.decode("utf-8", errors="replace")
                if stderr_text.strip():
                    print(f"  stderr: {stderr_text.strip()[:500]}", flush=True)
            return result
        except FileNotFoundError as e:
            print(f"  ERROR: docker command not found: {e}", file=sys.stderr)
            return subprocess.CompletedProcess([docker_cmd], returncode=127, stdout=b"", stderr=str(e).encode())
        except subprocess.TimeoutExpired:
            print(f"  ERROR: Command timed out after 120s", file=sys.stderr)
            return subprocess.CompletedProcess([docker_cmd], returncode=1, stdout=b"", stderr=b"TimeoutExpired")

    # Template mode: single string command (from compile_cmd_template etc.)
    if len(cmd_parts) == 1 and ("$(" in cmd_parts[0] or "`" in cmd_parts[0]):
        cmd_str = cmd_parts[0]
        print(f"  {label}: {cmd_str}", flush=True)
        try:
            result = subprocess.run(cmd_str, shell=True, cwd=cwd,
                                    capture_output=True, timeout=120)
            if result.returncode != 0 and result.stderr:
                stderr_text = result.stderr.decode("utf-8", errors="replace")
                if stderr_text.strip():
                    print(f"  stderr: {stderr_text.strip()[:500]}", flush=True)
            return result
        except FileNotFoundError as e:
            print(f"  ERROR: Command not found: {e}", file=sys.stderr)
            return subprocess.CompletedProcess(cmd_parts, returncode=127, stdout=b"", stderr=str(e).encode())
        except subprocess.TimeoutExpired:
            print(f"  ERROR: Command timed out after 120s", file=sys.stderr)
            return subprocess.CompletedProcess(cmd_parts, returncode=1, stdout=b"", stderr=b"TimeoutExpired")

    # Standard mode: list of parts
    cwd_abs = os.path.abspath(cwd)
    expanded_parts = []
    for part in cmd_parts:
        if os.path.isabs(part) and part.startswith(cwd_abs + os.sep):
            part = os.path.relpath(part, cwd_abs)
        expanded_parts.append(part)

    use_shell = any('$(' in part or '`' in part for part in expanded_parts)

    print_cmd = " ".join(expanded_parts) if use_shell else " ".join(expanded_parts)
    print(f"  {label}: {print_cmd}", flush=True)

    try:
        result = subprocess.run(
            " ".join(expanded_parts) if use_shell else expanded_parts,
            shell=use_shell,
            cwd=cwd,
            capture_output=True,
            timeout=120,
        )
        if result.returncode != 0 and result.stderr:
            stderr_text = result.stderr.decode("utf-8", errors="replace")
            if stderr_text.strip():
                print(f"  stderr: {stderr_text.strip()[:500]}", flush=True)
        return result
    except FileNotFoundError as e:
        print(f"  ERROR: Command not found: {e}", file=sys.stderr)
        return subprocess.CompletedProcess(expanded_parts, returncode=127, stdout=b"", stderr=str(e).encode())
    except subprocess.TimeoutExpired:
        print(f"  ERROR: Command timed out after 120s", file=sys.stderr)
        return subprocess.CompletedProcess(expanded_parts, returncode=1, stdout=b"", stderr=b"TimeoutExpired")


def build_caffc_cmd(cfg: TestConfig, test_dir: str) -> List[str]:
    """Build the caffc compilation command."""
    # Resolve glob in caffc jar path relative to test directory
    caffc_cmd = cfg.caffc_cmd
    # Resolve globs from within the test directory since paths like ../../target/caffc-*.jar
    # are relative to each test folder
    for pattern in glob.glob(os.path.join(test_dir, "**"), recursive=False):
        # Try resolving each token that looks like it might contain a glob
        pass

    # Find and resolve glob patterns in the command tokens
    cmd_tokens = caffc_cmd.split()
    resolved_tokens = []
    for token in cmd_tokens:
        if "*" in token or "?" in token or "[" in token:
            # Resolve from test directory
            abs_pattern = os.path.normpath(os.path.join(test_dir, token))
            matches = sorted(glob.glob(abs_pattern))
            if matches:
                # Replace the glob with the first match (or all if multiple jars expected)
                resolved_tokens.append(matches[0])
            else:
                resolved_tokens.append(token)
        else:
            resolved_tokens.append(token)

    cmd = resolved_tokens

    # Add -onefile if specified
    if cfg.caffc_onefile:
        cmd.extend(["-onefile", cfg.caffc_onefile])

    # Source files
    src_files = list(Path(test_dir).glob("src/**/*.caffc"))
    src_files.sort()
    if not src_files:
        print(f"  WARNING: No .caffc files found in {test_dir}/src/", file=sys.stderr)
    for f in src_files:
        cmd.append(str(f))

    # Output directory
    cmd.extend(["-o", "target/caffc-c/"])

    return cmd


def build_c_compile_cmd(cfg: TestConfig, c_files: List[str], obj_dir: str) -> List[List[str]]:
    """Build C compilation commands for each .c file -> .o.

    If compile_cmd_template is set, uses it with {file} and {output} placeholders.
    Otherwise uses compile_cmd + compile_flags with standard -c/-o arguments.
    """
    cmds = []
    for c_file in c_files:
        obj_name = Path(c_file).stem + ".o"
        obj_path = os.path.join(obj_dir, obj_name)

        if cfg.compile_cmd_template:
            cmd = cfg.compile_cmd_template.replace("{file}", c_file).replace("{output}", obj_path)
            cmds.append([cmd])
        else:
            cmd = cfg.compile_cmd.split() + cfg.compile_flags.split() + ["-c", c_file, "-o", obj_path]
            cmds.append(cmd)
    return cmds


def build_link_cmd(cfg: TestConfig, obj_files: List[str]) -> List[str]:
    """Build the linking command.

    If link_cmd_template is set, uses it with {objects} and {output} placeholders.
    Otherwise uses link_cmd + link_flags with standard object files.
    """
    if cfg.link_cmd_template:
        objs = " ".join(obj_files)
        return [cfg.link_cmd_template.replace("{objects}", objs).replace("{output}", "target/main")]
    cmd = cfg.link_cmd.split() + cfg.link_flags.split()
    cmd.extend(obj_files)
    cmd.extend(cfg.extra_libs.split())
    cmd.extend(["-o", "target/main"])
    return cmd


def build_execute_cmd(cfg: TestConfig) -> List[str]:
    """Build the execution command.

    If execute_cmd_template is set, uses it with {binary} placeholder.
    Otherwise uses execute_cmd with optional extra args.
    """
    if cfg.execute_cmd_template:
        cmd = cfg.execute_cmd_template.replace("{binary}", "target/main")
        return [cmd]
    cmd_str = cfg.execute_cmd if cfg.execute_cmd else "./target/main"
    cmd = cmd_str.split()
    if cfg.execute_extra_args:
        cmd.extend(cfg.execute_extra_args.split())
    return cmd


# ---------------------------------------------------------------------------
# Test execution
# ---------------------------------------------------------------------------

def run_test(test_name: str, base_dir: str, merged_config: TestConfig,
             dry_run: bool = False, verbose: bool = False,
             num_workers: int = 1,
             docker_ctx: Optional[DockerContainer] = None) -> TestResultSummary:
    """Run a single test with the merged configuration."""
    test_dir = os.path.join(base_dir, test_name)
    if not os.path.isdir(test_dir):
        return TestResultSummary(test_name, TestResult.ERROR, 0, f"Directory not found: {test_dir}")

    start_time = time.monotonic()

    caffc_c_dir = os.path.join(test_dir, "target", "caffc-c")
    obj_dir = os.path.join(test_dir, "target", "caffc-o")

  # Step 0: Clean target directory (like the shell scripts)
    if not dry_run:
        target_dir = os.path.join(test_dir, "target")
        if os.path.exists(target_dir):
            shutil.rmtree(target_dir)
        os.makedirs(caffc_c_dir)
        os.makedirs(obj_dir)

        caffc_cmd = build_caffc_cmd(merged_config, test_dir)
        result = run_step("caffc", caffc_cmd, test_dir)
        if result.returncode != 0:
            if result.returncode == 127:
                duration = time.monotonic() - start_time
                return TestResultSummary(test_name, TestResult.ERROR, duration,
                                         f"caffc command not found: {caffc_cmd[0]}")
            stderr = result.stderr.decode("utf-8", errors="replace") if result.stderr else ""
            stdout = result.stdout.decode("utf-8", errors="replace") if result.stdout else ""
            duration = time.monotonic() - start_time
            return TestResultSummary(test_name, TestResult.FAILED, duration,
                                     f"caffc failed (rc={result.returncode})\nstdout: {stdout}\nstderr: {stderr}")

    # Discover .c files
    c_files = list(Path(caffc_c_dir).glob("*.c"))
    if not c_files:
        duration = time.monotonic() - start_time
        return TestResultSummary(test_name, TestResult.ERROR, duration,
                                 "No .c files generated by caffc")

    # Discover extra .c sources from src/ (files that are not .caffc but .c)
    extra_c = list(Path(test_dir).glob("src/*.c"))
    # Also check any extra_c_sources paths
    if merged_config.extra_c_sources:
        sources_str = merged_config.extra_c_sources
        if isinstance(sources_str, list):
            sources_str = " ".join(sources_str)
        for pattern in sources_str.split():
            extra_c.extend(Path(test_dir).glob(pattern))

    # Step 2: C compile (each .c -> .o)
    if not dry_run:
        all_c_files = [str(f) for f in c_files] + [str(f) for f in extra_c]
        compile_cmds = build_c_compile_cmd(merged_config, all_c_files, obj_dir)

        def _compile_one(cmd_idx: int, cmd: List[str]) -> Tuple[int, int, subprocess.CompletedProcess]:
            result = run_step("cc", cmd, test_dir, docker_ctx=docker_ctx)
            return (cmd_idx, cmd_idx, result)

        if num_workers > 1 and len(compile_cmds) > 1:
            print(f"  Compiling {len(compile_cmds)} file(s) with {num_workers} worker(s)...", flush=True)
            compile_results = {}
            with concurrent.futures.ThreadPoolExecutor(max_workers=num_workers) as executor:
                futures = [executor.submit(_compile_one, i, cmd) for i, cmd in enumerate(compile_cmds)]
                for future in concurrent.futures.as_completed(futures):
                    cmd_idx, _, result = future.result()
                    compile_results[cmd_idx] = result
                    if result.returncode != 0:
                        stderr = result.stderr.decode("utf-8", errors="replace") if result.stderr else ""
                        duration = time.monotonic() - start_time
                        return TestResultSummary(test_name, TestResult.FAILED, duration,
                                                 f"compile failed (rc={result.returncode})\nstderr: {stderr}")
        else:
            for cmd in compile_cmds:
                result = run_step("cc", cmd, test_dir, docker_ctx=docker_ctx)
                if result.returncode != 0:
                    stderr = result.stderr.decode("utf-8", errors="replace") if result.stderr else ""
                    duration = time.monotonic() - start_time
                    return TestResultSummary(test_name, TestResult.FAILED, duration,
                                             f"compile failed (rc={result.returncode})\nstderr: {stderr}")

    # Step 3: Link
    if not dry_run:
        obj_files = list(Path(obj_dir).glob("*.o"))
        if not obj_files:
            duration = time.monotonic() - start_time
            return TestResultSummary(test_name, TestResult.ERROR, duration,
                                     "No .o files generated")
        obj_files.sort()
        obj_paths = [str(f) for f in obj_files]
        link_cmd = build_link_cmd(merged_config, obj_paths)
        result = run_step("ld", link_cmd, test_dir, docker_ctx=docker_ctx)
        if result.returncode != 0:
            duration = time.monotonic() - start_time
            return TestResultSummary(test_name, TestResult.FAILED, duration,
                                     f"link failed (rc={result.returncode})")

    # Step 4: Execute
    if not dry_run:
        exec_cmd = build_execute_cmd(merged_config)
        result = run_step("exec", exec_cmd, test_dir, docker_ctx=docker_ctx)
        duration = time.monotonic() - start_time
        stdout = result.stdout.decode("utf-8", errors="replace") if result.stdout else ""
        if stdout.strip():
            print(stdout.rstrip(), flush=True)
        if result.returncode != 0:
            msg = f"execution failed (rc={result.returncode})"
            return TestResultSummary(test_name, TestResult.FAILED, duration, msg)
        return TestResultSummary(test_name, TestResult.PASSED, duration)
    else:
        duration = time.monotonic() - start_time
        # In dry-run mode, just show what would be done
        return TestResultSummary(test_name, TestResult.PASSED, duration, "(dry-run)")


# ---------------------------------------------------------------------------
# Main runner
# ---------------------------------------------------------------------------

def discover_tests(base_dir: str) -> List[str]:
    """Discover test directories (subdirectories with .caffc sources)."""
    tests = []
    for entry in sorted(os.listdir(base_dir)):
        test_dir = os.path.join(base_dir, entry)
        if os.path.isdir(test_dir):
            src_dir = os.path.join(test_dir, "src")
            if os.path.isdir(src_dir):
                caffc_files = list(Path(src_dir).glob("**/*.caffc"))
                if caffc_files:
                    tests.append(entry)
    return tests


def print_banner(title: str, profile_name: str):
    print("")
    print("=" * 72)
    print(f"  CaffC Test Runner  |  Profile: {profile_name}")
    print(f"  {'#' * 56}")
    print(f"  {title}")
    print("=" * 72)


def print_results(results: List[TestResultSummary], show_all: bool = True):
    """Print a summary of all test results."""
    print("")
    print("-" * 72)
    print(f"  {'Test':<40} {'Status':<10} {'Time'}")
    print("-" * 72)

    for r in results:
        status_str = r.result.value.upper()
        time_str = f"{r.duration_sec:.2f}s"
        if show_all or r.result != TestResult.PASSED:
            print(f"  {r.test_name:<40} {status_str:<10} {time_str}")

    print("-" * 72)

    # Summary
    passed = sum(1 for r in results if r.result == TestResult.PASSED)
    failed = sum(1 for r in results if r.result == TestResult.FAILED)
    errors = sum(1 for r in results if r.result == TestResult.ERROR)
    total = len(results)
    print(f"  Total: {total}  Passed: {passed}  Failed: {failed}  Errors: {errors}")
    print("-" * 72)

    if failed or errors:
        print("")
        print("  FAILED TESTS:")
        for r in results:
            if r.result != TestResult.PASSED:
                print(f"    {r.test_name}: {r.message}")
        print("")


def _twidth(s: str) -> int:
    """Terminal display width (emojis occupy 2 columns)."""
    w = 0
    for c in s:
        w += 2 if ord(c) > 0x2700 else 1
    return w


def _tpad(text: str, width: int, align: str = "center") -> str:
    """Pad text to a field of *width* terminal columns.
    
    When align='center', computes left/right padding so that text is visually
    centered. Uses _twidth for terminal-aware measurement.
    """
    tw = _twidth(text)
    if tw >= width:
        return text
    pad = width - tw
    if align == "center":
        left = pad // 2
    elif align == "left":
        left = 0
    else:
        left = pad  # right
    right = pad - left
    return " " * left + text + " " * right


def print_matrix_results(results: Dict[str, Dict[str, TestResultSummary]], tests_to_run: List[str]):
    """Print a matrix-style results table with tests as rows and profiles as columns."""
    emoji_map = {"passed": "✅", "failed": "❌", "error": "⚠️"}
    profile_names = list(results.keys())

    test_name_width = max(len(t) for t in tests_to_run) if tests_to_run else 20
    test_name_width = max(test_name_width, 4)

    profile_name_width = max(len(p) for p in profile_names) if profile_names else 10
    profile_name_width = max(profile_name_width, 10)

    # Each profile column gets the same cell width in terminal columns.
    # We need enough room for the profile name header and the data cell
    # ("✅ 12.34s" has twidth = 2+1+6 = 9).
    cell_width = profile_name_width + 10

    row_total_width = test_name_width + 2 + cell_width * len(profile_names) + 2

    # Pre-compute header padding per column so data aligns with header.
    # left_pad[col] = number of space characters before the header text.
    col_left_pad = []
    for pname in profile_names:
        tw = len(pname)  # ASCII profile names, so len == twidth
        lp = (cell_width - tw) // 2
        col_left_pad.append(lp)

    print("")
    print("=" * row_total_width)

    # Header row
    header = f"  {'Test':<{test_name_width}}"
    for i, pname in enumerate(profile_names):
        lp = col_left_pad[i]
        rp = cell_width - lp - len(pname)
        header += f"  {' ' * lp}{pname}{' ' * rp}"
    print(header)
    print("=" * row_total_width)

    # Data rows
    total_passed = 0
    total_failed = 0
    total_errors = 0
    total_tests = 0

    for test_name in tests_to_run:
        row = f"  {test_name:<{test_name_width}}"
        for i, pname in enumerate(profile_names):
            result = results[pname].get(test_name)
            lp = col_left_pad[i]
            if result:
                status_str = emoji_map[result.result.value]
                time_str = f"{result.duration_sec:.2f}s"
                cell = f"{status_str} {time_str}"
                rp = max(1, cell_width - lp - _twidth(cell))
                row += f"  {' ' * lp}{cell}{' ' * rp}"
                total_tests += 1
                if result.result == TestResult.PASSED:
                    total_passed += 1
                elif result.result == TestResult.FAILED:
                    total_failed += 1
                elif result.result == TestResult.ERROR:
                    total_errors += 1
            else:
                cell = "N/A"
                rp = max(1, cell_width - lp - _twidth(cell))
                row += f"  {' ' * lp}{cell}{' ' * rp}"
        print(row)

    print("=" * row_total_width)
    print(f"  Total: {total_tests}  Passed: {total_passed}  Failed: {total_failed}  Errors: {total_errors}")
    print("=" * row_total_width)

    # Show failures
    if total_failed or total_errors:
        print("")
        print("  FAILED TESTS:")
        for pname in profile_names:
            for test_name in tests_to_run:
                result = results[pname].get(test_name)
                if result and result.result != TestResult.PASSED:
                    print(f"    {pname}/{test_name}: {result.message}")
        print("")


# ---------------------------------------------------------------------------
# Inner test runner (extracted for docker/non-docker code path)
# ---------------------------------------------------------------------------

def _run_test_inner(test_name: str, base_dir: str, defaults: TestConfig,
                    profile: Profile, args: argparse.Namespace,
                    results: Dict[str, Dict[str, TestResultSummary]],
                    display_name: str, num_profiles: int,
                    docker_ctx: Optional[DockerContainer]):
    """Run a single test within a profile loop."""
    test_dir = os.path.join(base_dir, test_name)
    project_config = load_project_config(test_dir)

    merged = merge_configs(defaults, profile, project_config)

    if not args.quiet and num_profiles == 1:
        print(f"\n>>> {test_name}")

    result = run_test(test_name, base_dir, merged,
                      dry_run=args.dry_run, verbose=args.verbose,
                      num_workers=args.jobs, docker_ctx=docker_ctx)
    results[display_name][test_name] = result

    status_icon = {"passed": "[PASS]", "failed": "[FAIL]", "error": "[ERR ]"}[result.result.value]
    time_str = f"{result.duration_sec:.2f}s"
    if not args.quiet and num_profiles == 1:
        if result.message:
            print(f"  {status_icon} {test_name} ({time_str}) - {result.message}")
        else:
            print(f"  {status_icon} {test_name} ({time_str})")
    else:
        print(f"  {status_icon} {test_name} ({time_str})")

    if args.fail_fast and result.result != TestResult.PASSED:
        return True  # signal to break
    return False


# ---------------------------------------------------------------------------
# CLI
# ---------------------------------------------------------------------------

def main():
    parser = argparse.ArgumentParser(
        description="Flexible CaffC test runner with configurable profiles.",
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog="""
Profiles:
  Built-in profiles: gcc, c89, clang, gcc_release, gcc_valgrind, gcc_asan, gcc_tsan
  Custom profiles: place .yaml files in the 'profiles/' directory

Per-project overrides:
  Place a 'run-tests-config.yaml' file in each test directory with a
  'config:' section containing any of the configurable fields.

Command overrides (useful for Docker/EMulation):
  --caffc-cmd      Command to run caffc (supports shell-style with args)
  --compile-cmd    C compiler command (e.g. 'docker run ... powerpc64le-gcc')
  --link-cmd       Linker command
  --execute-cmd    Execution command (e.g. 'qemu-ppc64le ... ./target/main')

  --compile-flags  C compiler flags
  --link-flags     Linker flags
  --extra-libs     Extra libraries to link (e.g. '-lm -lpthread')
        """,
    )

    parser.add_argument("tests", nargs="*", help="Test names to run (default: all)")
    parser.add_argument("-p", "--profile", action="append", default=[],
                        help="Profile name or path to profile YAML file (repeatable, comma-separated, default: gcc)")
    parser.add_argument("--profiles-dir", default="profiles",
                        help="Directory to load custom profiles from (default: profiles/)")
    parser.add_argument("-C", "--caffc-cmd", default="",
                        help="Override caffc command (e.g. 'java -jar /path/to/caffc.jar')")
    parser.add_argument("--compile-cmd", default="",
                        help="Override C compiler command (e.g. 'gcc', 'clang', 'docker run ...')")
    parser.add_argument("--link-cmd", default="",
                        help="Override linker command (e.g. 'gcc', 'clang', 'docker run ...')")
    parser.add_argument("--execute-cmd", default="",
                        help="Override execution command (e.g. './target/main', 'qemu-ppc64le ./target/main')")
    parser.add_argument("--caffc-flags", default="",
                        help="Extra flags for caffc compilation")
    parser.add_argument("--compile-flags", default="",
                        help="Extra C compiler flags (appended to profile flags)")
    parser.add_argument("--link-flags", default="",
                        help="Extra linker flags (appended to profile flags)")
    parser.add_argument("--extra-libs", default="",
                        help="Extra libraries to link (e.g. '-lm -lpthread')")
    parser.add_argument("--extra-c-sources", default="",
                        help="Extra C source files for compilation (glob patterns or paths)")
    parser.add_argument("--caffc-onefile", default="",
                        help="Use -onefile <name> for caffc output (e.g. 'main.c')")
    parser.add_argument("--verbose", "-v", action="store_true",
                        help="Verbose output")
    parser.add_argument("--dry-run", action="store_true",
                        help="Show what would be done without executing")
    parser.add_argument("--fail-fast", action="store_true",
                        help="Stop after the first failure")
    parser.add_argument("--quiet", "-q", action="store_true",
                        help="Only show test results, not individual steps")
    parser.add_argument("-j", "--jobs", type=int, default=8,
                        help="Number of parallel compile jobs (default: 1)")

    args = parser.parse_args()

    # Default to gcc if no profiles specified
    profile_specs = args.profile if args.profile else ["gcc"]
    # Split comma-separated profiles (e.g., "-p gcc,c89" -> ["gcc", "c89"])
    expanded_specs = []
    for spec in profile_specs:
        expanded_specs.extend(s.strip() for s in spec.split(",") if s.strip())
    profile_specs = expanded_specs if profile_specs else ["gcc"]

    # Resolve base directory
    base_dir = os.path.dirname(os.path.abspath(__file__))

    # Load all available profiles from profiles/ directory
    profiles = load_profiles_from_dir(os.path.join(base_dir, args.profiles_dir))

    # Resolve each profile spec to a Profile object with display name
    resolved_profiles: List[Tuple[str, Profile]] = []  # (display_name, Profile)
    for spec in profile_specs:
        spec_path = Path(spec)
        if spec_path.is_file():
            profile = load_profile_from_file(spec_path.resolve())
            # Use filename stem as display name
            display_name = spec_path.stem
            resolved_profiles.append((display_name, profile))
        elif spec in profiles:
            resolved_profiles.append((spec, profiles[spec]))
        else:
            print(f"ERROR: Unknown profile '{spec}'. Available: {', '.join(profiles.keys())}",
                  file=sys.stderr)
            sys.exit(1)

    # Discover tests
    all_tests = discover_tests(base_dir)
    if not all_tests:
        print("ERROR: No test directories found.", file=sys.stderr)
        sys.exit(1)

    # Filter requested tests
    if args.tests:
        # Support wildcards for test names
        import fnmatch
        selected = []
        for pattern in args.tests:
            for t in all_tests:
                if fnmatch.fnmatch(t, pattern):
                    selected.append(t)
        if not selected:
            print(f"ERROR: No tests matched: {args.tests}", file=sys.stderr)
            print(f"Available tests: {', '.join(all_tests)}", file=sys.stderr)
            sys.exit(1)
        tests_to_run = selected
    else:
        tests_to_run = all_tests

    # Print banner
    profile_names = ", ".join(display_name for display_name, _ in resolved_profiles)
    print_banner(f"Running {len(tests_to_run)} test(s) x {len(resolved_profiles)} profile(s)", profile_names)

    # Print effective config summary
    if not args.quiet and len(resolved_profiles) == 1:
        display_name, profile = resolved_profiles[0]
        defaults = TestConfig()
        for attr in vars(TestConfig()):
            val = getattr(profile.config, attr)
            if val:
                setattr(defaults, attr, val)

        # Apply CLI overrides on top
        for attr in ["caffc_cmd", "compile_cmd", "link_cmd", "execute_cmd"]:
            if getattr(args, attr.replace("-", "_"), ""):
                setattr(defaults, attr, getattr(args, attr.replace("-", "_")))

        # Apply CLI flags (appended to profile flags)
        if args.caffc_flags:
            defaults.caffc_flags = (defaults.caffc_flags + " " + args.caffc_flags).strip()
        if args.compile_flags:
            defaults.compile_flags = (defaults.compile_flags + " " + args.compile_flags).strip()
        if args.link_flags:
            defaults.link_flags = (defaults.link_flags + " " + args.link_flags).strip()
        if args.extra_libs:
            defaults.extra_libs = (defaults.extra_libs + " " + args.extra_libs).strip()
        if args.extra_c_sources:
            defaults.extra_c_sources = (defaults.extra_c_sources + " " + args.extra_c_sources).strip()
        if args.caffc_onefile:
            defaults.caffc_onefile = args.caffc_onefile

        print(f"\n  Profile:      {display_name}")
        print(f"  caffc:        {defaults.caffc_cmd}")
        if defaults.docker_container_name:
            print(f"  docker:       {defaults.docker_container_name} (shared container)")
        if defaults.compile_cmd_template:
            print(f"  compile:      (template: {defaults.compile_cmd_template[:80]}...)")
        else:
            print(f"  compile:      {defaults.compile_cmd} {defaults.compile_flags}")
        if defaults.link_cmd_template:
            print(f"  link:         (template: {defaults.link_cmd_template[:80]}...)")
        else:
            print(f"  link:         {defaults.link_cmd} {defaults.link_flags}")
        if defaults.execute_cmd_template:
            print(f"  execute:      (template: {defaults.execute_cmd_template[:80]}...)")
        else:
            print(f"  execute:      {defaults.execute_cmd}")
        if defaults.extra_libs:
            print(f"  extra libs:   {defaults.extra_libs}")
        print()

    # Run tests for each profile
    # results[profile_display_name][test_name] = TestResultSummary
    results: Dict[str, Dict[str, TestResultSummary]] = {
        display_name: {} for display_name, _ in resolved_profiles
    }

    for display_name, profile in resolved_profiles:
        # Build config for this profile
        defaults = TestConfig()
        for attr in vars(TestConfig()):
            val = getattr(profile.config, attr)
            if val:
                setattr(defaults, attr, val)

        # Apply CLI overrides on top
        for attr in ["caffc_cmd", "compile_cmd", "link_cmd", "execute_cmd"]:
            if getattr(args, attr.replace("-", "_"), ""):
                setattr(defaults, attr, getattr(args, attr.replace("-", "_")))

        # Apply CLI flags (appended to profile flags)
        if args.caffc_flags:
            defaults.caffc_flags = (defaults.caffc_flags + " " + args.caffc_flags).strip()
        if args.compile_flags:
            defaults.compile_flags = (defaults.compile_flags + " " + args.compile_flags).strip()
        if args.link_flags:
            defaults.link_flags = (defaults.link_flags + " " + args.link_flags).strip()
        if args.extra_libs:
            defaults.extra_libs = (defaults.extra_libs + " " + args.extra_libs).strip()
        if args.extra_c_sources:
            defaults.extra_c_sources = (defaults.extra_c_sources + " " + args.extra_c_sources).strip()
        if args.caffc_onefile:
            defaults.caffc_onefile = args.caffc_onefile

        if not args.quiet and len(resolved_profiles) > 1:
            print(f"\n{'=' * 72}")
            print(f"  Profile: {display_name}")
            print(f"{'=' * 72}")

        # Determine if this profile uses a shared Docker container
        is_docker_profile = bool(defaults.docker_run_command)

        if is_docker_profile and not args.dry_run:
            # Create Docker container context (starts container once, stops after all tests)
            docker_ctx = DockerContainer(defaults, os.path.join(base_dir, args.profiles_dir))
        else:
            docker_ctx = None

        if is_docker_profile and not args.dry_run:
            with docker_ctx:
                for test_name in tests_to_run:
                    should_stop = _run_test_inner(
                        test_name, base_dir, defaults, profile, args,
                        results, display_name, len(resolved_profiles), docker_ctx)
                    if should_stop:
                        break
        else:
            for test_name in tests_to_run:
                should_stop = _run_test_inner(
                    test_name, base_dir, defaults, profile, args,
                    results, display_name, len(resolved_profiles), docker_ctx)
                if should_stop:
                    break

    # Print matrix results
    print_matrix_results(results, tests_to_run)

    # Exit code
    any_failure = any(
        r.result != TestResult.PASSED
        for profile_results in results.values()
        for r in profile_results.values()
    )
    sys.exit(1 if any_failure else 0)


if __name__ == "__main__":
    main()
