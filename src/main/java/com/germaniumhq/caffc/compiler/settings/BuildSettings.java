package com.germaniumhq.caffc.compiler.settings;

import com.germaniumhq.caffc.args.ArgumentsParser;
import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.settings.common.CommonSettings;
import com.germaniumhq.caffc.compiler.settings.debug.DebugSettings;
import com.germaniumhq.caffc.compiler.settings.exception.ExceptionSettings;
import com.germaniumhq.caffc.compiler.settings.gc.GcSettings;
import com.germaniumhq.caffc.compiler.settings.gc.GcSettingsDefault;
import com.germaniumhq.caffc.compiler.settings.string.StringSettings;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class BuildSettings {
    private String exeName;
    private String templatesFolder;
    private String outputFolder = "caffc-out";
    private String oneFile;
    private List<String> inputSources = new ArrayList<>();

    public CommonSettings common = new CommonSettings();
    public GcSettings gc = new GcSettingsDefault();
    public DebugSettings debug = new DebugSettings();
    public StringSettings string = new StringSettings();
    public ExceptionSettings exception = new ExceptionSettings();

    public BuildSettings() {
        readBuildSettingsFromEnvironment();
    }

    public static <T extends Enum<T>> T parseEnum(Class<T> enumClass, String value, String fieldName) {
        try {
            return Enum.valueOf(enumClass, value);
        } catch (IllegalArgumentException e) {
            CaffcCompiler.get().fatal(SourceLocation.fromFilePath("caffc.yaml"),
                String.format(
                    "Invalid %s value: %s. Allowed values are: %s",
                    fieldName,
                    value,
                    List.of(enumClass.getEnumConstants())
                    ));
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static void readFromFile(BuildSettings settings, String projectConfigFile) {
        File file = new File(projectConfigFile);
        if (!file.exists()) {
            return;
        }

        try (InputStream is = new FileInputStream(file)) {
            Yaml yaml = new Yaml();
            @SuppressWarnings("unchecked")
            Map<String, Object> config = yaml.load(is);
            if (config == null) {
                return;
            }

            if (config.containsKey("one_file")) {
                settings.oneFile = config.get("one_file").toString();
            }

            Map<String, Object> gcConfig = (Map<String, Object>) config.get("gc");
            if (gcConfig != null) {
                String impl = readFeatureImpl(settings, gcConfig);
                settings.gc = GcSettings.createFromName(impl);
                settings.gc.readFrom(gcConfig);
            }

            Map<String, Object> debugConfig = (Map<String, Object>) config.get("debug");
            if (debugConfig != null) {
                settings.debug.readFrom(debugConfig);
            }

            Map<String, Object> stringConfig = (Map<String, Object>) config.get("string");
            if (stringConfig != null) {
                settings.string.readFrom(stringConfig);
            }

            Map<String, Object> commonConfig = (Map<String, Object>) config.get("common");
            if (commonConfig != null) {
                settings.common.readFrom(commonConfig);
            }

            Map<String, Object> exceptionConfig = (Map<String, Object>) config.get("exception");
            if (exceptionConfig != null) {
                settings.exception.readFrom(exceptionConfig);
            }
        } catch (Exception e) {
            CaffcCompiler.get().fatal(SourceLocation.fromFilePath("caffc.yaml"),
                "Failed to read config file: " + projectConfigFile + ": " + e.getMessage());
        }
    }

    private static String readFeatureImpl(BuildSettings settings, Map<String, Object> featureConfiguration) {
        Object impl = featureConfiguration.get("impl");

        if (impl != null) {
            return impl.toString();
        }

        return "default";
    }

    private void readBuildSettingsFromEnvironment() {
        // The "program" that is running. If the name is a `.jar` file, it means, we're
        // executed as `java -jar caffc.jar ...`. Otherwise, it's a graalvm instance.
        URL location = ArgumentsParser.class.getProtectionDomain().getCodeSource().getLocation();
        this.exeName = location.getFile();

        // detect where the templates are.
        String caffcHome = System.getenv().get("CAFFC_HOME");

        if (caffcHome != null) {
            this.templatesFolder = Path.of(caffcHome, "templates")
                    .toAbsolutePath().toString();
            return;
        }

        String exeFolder = new File(this.exeName).getParentFile().getAbsolutePath();
        this.templatesFolder = Path.of(exeFolder, "../templates")
                .toAbsolutePath().toString();
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    public String getOneFile() {
        return oneFile;
    }

    public void setOneFile(String oneFile) {
        this.oneFile = oneFile;
    }

    public String getFeatureSetting(String featureName) {
        switch (featureName) {
            case "common": return common.implName();
            case "gc": return gc.implName();
            case "string": return string.implName();
            case "exception": return exception.implName();
            default:
                CaffcCompiler.get().fatal(SourceLocation.UNKNOWN, "Invalid feature: " + featureName);
        }

        return null;
    }

    public String getTemplatesFolder() {
        return templatesFolder;
    }

    public void setTemplatesFolder(String templatesFolder) {
        this.templatesFolder = templatesFolder;
    }

    public List<String> getInputSources() {
        return inputSources;
    }

    /**
     * Resolve a dotted caffc.yaml-style path to a scalar for compile-time {@code #switch}.
     * Returns {@link Number}, {@link String}, or {@link Boolean}. May return {@code null}
     * when the setting exists but is unset (e.g. {@code one_file}, {@code string.locale}).
     */
    public Object getCompileTimeValue(String dottedPath, SourceLocation sourceLocation) {
        switch (dottedPath) {
            case "one_file":
                return oneFile;
            case "gc.impl":
                return gc.implName();
            case "gc.memory_trigger":
                if (gc instanceof GcSettingsDefault gcDefault) {
                    return gcDefault.getMemoryTrigger();
                }
                CaffcCompiler.get().fatal(sourceLocation,
                        "compile-time setting `gc.memory_trigger` is only available for gc.impl=default");
                return null;
            case "debug.c_line_macro":
                return debug.cLineMacro.name();
            case "debug.trace_line_runtime":
                return debug.traceLineRuntime.name();
            case "string.locale":
                return string.getLocale();
            case "string.impl":
                return string.implName();
            case "string.locale_list":
                CaffcCompiler.get().fatal(sourceLocation,
                        "compile-time setting `string.locale_list` is not a scalar");
                return null;
            case "common.impl":
                return common.implName();
            case "exception.impl":
                return exception.implName();
            default:
                CaffcCompiler.get().fatal(sourceLocation,
                        "unknown compile-time setting `" + dottedPath + "`");
                return null;
        }
    }
}
