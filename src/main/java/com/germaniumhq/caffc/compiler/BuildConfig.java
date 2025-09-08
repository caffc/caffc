package com.germaniumhq.caffc.compiler;

import com.germaniumhq.caffc.args.ArgumentsParser;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildConfig {
    private String exeName;
    private String templatesFolder;
    private String outputFolder = "caffc-out";
    private List<String> inputSources = new ArrayList<>();
    private Map<String, String> features = new HashMap<>();

    public BuildConfig() {
        // The "program" that is running. If the name is a `.jar` file, it means, we're
        // executed as `java -jar caffc.jar ...`. Otherwise it's a graalvm instance.
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

    public String getFeatureSetting(String featureName, String defaultValue) {
        return features.getOrDefault(featureName, defaultValue);
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

    public void setFeature(String featureName, String profile) {
        this.features.put(featureName, profile);
    }
}
