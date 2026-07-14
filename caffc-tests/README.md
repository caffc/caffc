# CaffC Integration Tests

Integration tests for the CaffC compiler. Each test is a directory under this folder, containing `.caffc` source files and a `target/` directory for build outputs.

## Directory Structure

Each test directory follows this layout:

```
<test-name>/
  src/
    <main>.caffc          # CaffC source files (recursively discovered)
    extra.c               # (optional) Extra C source files to compile
  run-tests-config.yaml   # (optional) Per-project overrides
  target/                 # Build output (auto-cleaned by run-tests.py)
    caffc-c/              # Generated .c files
    caffc-o/              # Compiled .o object files
    main                  # Final executable
```

## Test Discovery

Tests are discovered automatically: any subdirectory containing at least one `.caffc` file under `src/` is treated as a test. The test pipeline:

1. **CaffC compilation** — runs the caffc compiler on `src/**/*.caffc`, outputs `.c` files to `target/caffc-c/`
2. **C compilation** — compiles each `.c` file (including any extra `.c` sources) to `.o` in `target/caffc-o/`
3. **Linking** — links all `.o` files into `target/main`
4. **Execution** — runs `target/main` and checks the exit code

The `target/` directory is cleaned before each test run.

## Python Test Runner (`run-tests.py`)

A flexible test runner with configurable profiles. No external Python dependencies — includes a built-in minimal YAML parser.

### Usage

```bash
# Run all tests with default profile (gcc)
python3 run-tests.py

# Run specific tests
python3 run-tests.py hello-world-caffc string-tests

# Run with a specific profile
python3 run-tests.py --profile clang

# Run with multiple profiles (matrix mode)
python3 run-tests.py --profile gcc --profile clang

# Override commands/flags on the command line
python3 run-tests.py --compile-flags "-Wall -O2" --link-flags "-lm"

# Dry-run to see what would be executed
python3 run-tests.py --dry-run

# Parallel compilation
python3 run-tests.py -j 4

# Stop on first failure
python3 run-tests.py --fail-fast
```

### Options

| Flag | Description |
|------|-------------|
| `tests...` | Test names to run (supports fnmatch wildcards). Default: all |
| `-p, --profile` | Profile name or path to YAML file (repeatable, comma-separated). Default: `gcc` |
| `--profiles-dir` | Custom profiles directory. Default: `profiles/` |
| `--caffc-cmd` | Override caffc compiler command |
| `--compile-cmd` | Override C compiler command |
| `--link-cmd` | Override linker command |
| `--execute-cmd` | Override execution command |
| `--caffc-flags` | Extra flags for caffc |
| `--compile-flags` | Extra C compiler flags (appended to profile flags) |
| `--link-flags` | Extra linker flags (appended to profile flags) |
| `--extra-libs` | Extra libraries to link (e.g. `-lm`) |
| `--extra-c-sources` | Extra C source file patterns |
| `--caffc-onefile` | Use `-onefile <name>` for unified output |
| `--verbose, -v` | Verbose output |
| `--dry-run` | Show commands without executing |
| `--fail-fast` | Stop after first failure |
| `--quiet, -q` | Minimal output |
| `-j, --jobs` | Parallel compile jobs (default: 1) |

## Profiles

Profiles define the commands and flags for the 4 pipeline stages: caffc, compile, link, execute. Settings are hierarchical: **defaults <- profile <- per-project overrides**.

### Built-in Profiles

| Profile | C Compiler | Flags | Notes |
|---------|-----------|-------|-------|
| `gcc` | gcc | `-Wall -g -ggdb` | Default profile |
| `c89` | c89 | `-Wall -g -ggdb` | C89 compliance |
| `clang` | clang | `-Wall -g -ggdb` | Clang compiler |
| `gcc_release` | gcc | `-O2 -DNDEBUG` | Optimized build |
| `gcc_valgrind` | gcc | `-Wall -g -ggdb` | Run with valgrind |
| `gcc_asan` | gcc | `-Wall -g -ggdb -fsanitize=address -fsanitize=undefined` | Address/UB sanitizers |
| `gcc_tsan` | gcc | `-Wall -g -ggdb -fsanitize=thread` | Thread sanitizer |

### Custom Profiles

Custom profiles live in the `profiles/` directory as YAML files. Each file defines a `config:` section with any subset of the configurable fields:

```yaml
# profiles/myprofile.yaml
config:
  caffc_cmd: java -jar /path/to/caffc.jar
  compile_cmd: gcc
  compile_flags: "-Wall -O2"
  link_cmd: gcc
  link_flags: "-g -lm"
```

For cross-compilation or containerized builds, use template fields with placeholders:

```yaml
config:
  compile_cmd_template: |
    docker run --rm -v $(pwd):$(pwd) -w $(pwd) myimage gcc -c {file} -o {output}
  link_cmd_template: |
    docker run --rm -v $(pwd):$(pwd) -w $(pwd) myimage gcc {objects} -o {output}
  execute_cmd_template: |
    docker run --rm -v $(pwd):$(pwd) -w $(pwd) myimage ./target/main
```

Template placeholders: `{file}`, `{output}`, `{objects}`, `{binary}`.

### Per-Project Overrides

Place a `run-tests-config.yaml` in any test directory to override profile settings for that test only:

```yaml
# hello-world-caffc/run-tests-config.yaml
config:
  caffc_onefile: "main.c"
```

## Output

The runner prints a status line per test (`[PASS]`, `[FAIL]`, `[ERR]`) and a matrix summary table when running multiple profiles. Exit code is 0 if all tests pass, 1 otherwise.
