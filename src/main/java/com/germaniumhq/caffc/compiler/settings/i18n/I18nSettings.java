package com.germaniumhq.caffc.compiler.settings.i18n;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.settings.CaffcFeature;
import com.germaniumhq.caffc.compiler.settings.FilesSetting;

import java.util.Map;

/**
 * Settings for the {@code i18n} feature package.
 *
 * <pre>
 * i18n:
 *   files:
 *     includes:
 *       - glob("generated/CodePage8859_2.caffc")
 *     excludes: []
 * </pre>
 */
public class I18nSettings implements CaffcFeature {
    /**
     * Default: ship all pre-packaged generated code pages.
     * Note: Java globs treat double-star as directories, so both
     * {@code generated/*.caffc} and nested {@code generated/.../*.caffc} patterns
     * are included.
     */
    public FilesSetting files = FilesSetting.withDefaultIncludes(
            "generated/*.caffc",
            "generated/**/*.caffc");

    @Override
    public String implName() {
        return "default";
    }

    @Override
    public void readFrom(Map<String, Object> config) {
        if (config.containsKey("files")) {
            files.readFrom(config.get("files"), SourceLocation.fromFilePath("caffc.yaml"));
        }
    }
}
