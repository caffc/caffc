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
 *       - glob("generated/iso/CodePage8859_2.caffc")
 *     excludes: []
 * </pre>
 */
public class I18nSettings implements CaffcFeature {
    /**
     * Selected generated code-page files. Empty by default — projects opt in via
     * {@code caffc.yaml} {@code i18n.files.includes} globs.
     */
    public FilesSetting files = new FilesSetting();

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
