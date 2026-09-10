package com.germaniumhq.caffc.compiler.settings;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Bazel-style file selection: {@code includes} / {@code excludes} arrays of
 * {@code glob("pattern")} entries (plain patterns are also accepted).
 *
 * <p>Used from compile-time expressions as {@code i18n.files.contains("path")}.
 */
public final class FilesSetting {
    private static final Pattern GLOB_CALL = Pattern.compile(
            "^glob\\(\\s*[\"']([^\"']+)[\"']\\s*\\)$");

    private final List<String> includeGlobs = new ArrayList<>();
    private final List<String> excludeGlobs = new ArrayList<>();

    public static FilesSetting withDefaultIncludes(String... globs) {
        FilesSetting setting = new FilesSetting();
        for (String glob : globs) {
            setting.includeGlobs.add(glob);
        }
        return setting;
    }

    @SuppressWarnings("unchecked")
    public void readFrom(Object filesConfig, SourceLocation sourceLocation) {
        includeGlobs.clear();
        excludeGlobs.clear();

        if (filesConfig == null) {
            return;
        }

        if (!(filesConfig instanceof Map<?, ?> map)) {
            CaffcCompiler.get().fatal(sourceLocation,
                    "`files` must be a map with `includes` / `excludes` arrays");
            return;
        }

        Object includes = map.get("includes");
        if (includes != null) {
            readGlobList(includes, includeGlobs, "includes", sourceLocation);
        }

        Object excludes = map.get("excludes");
        if (excludes != null) {
            readGlobList(excludes, excludeGlobs, "excludes", sourceLocation);
        }
    }

    private static void readGlobList(
            Object value,
            List<String> target,
            String fieldName,
            SourceLocation sourceLocation) {
        if (!(value instanceof List<?> list)) {
            CaffcCompiler.get().fatal(sourceLocation,
                    "`files." + fieldName + "` must be an array of glob(...) entries");
            return;
        }
        for (Object item : list) {
            if (item == null) {
                continue;
            }
            target.add(parseGlobEntry(item.toString(), fieldName, sourceLocation));
        }
    }

    public static String parseGlobEntry(String raw, String fieldName, SourceLocation sourceLocation) {
        String trimmed = raw.trim();
        Matcher matcher = GLOB_CALL.matcher(trimmed);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        // Plain pattern also accepted (same as glob("pattern")).
        if (trimmed.contains("(") || trimmed.contains(")")) {
            CaffcCompiler.get().fatal(sourceLocation,
                    "invalid `files." + fieldName + "` entry: " + raw
                            + " (expected glob(\"pattern\") or a plain glob pattern)");
            return trimmed;
        }
        return trimmed;
    }

    /**
     * True when {@code path} matches any include glob and no exclude glob.
     * Paths are matched with forward slashes (bazel-style).
     */
    public boolean contains(String path) {
        String normalized = normalizePath(path);
        if (includeGlobs.isEmpty()) {
            return false;
        }
        boolean included = false;
        for (String glob : includeGlobs) {
            if (matches(glob, normalized)) {
                included = true;
                break;
            }
        }
        if (!included) {
            return false;
        }
        for (String glob : excludeGlobs) {
            if (matches(glob, normalized)) {
                return false;
            }
        }
        return true;
    }

    private static boolean matches(String glob, String path) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
        return matcher.matches(Path.of(path));
    }

    private static String normalizePath(String path) {
        return path.replace('\\', '/');
    }

    public List<String> getIncludeGlobs() {
        return includeGlobs;
    }

    public List<String> getExcludeGlobs() {
        return excludeGlobs;
    }
}
