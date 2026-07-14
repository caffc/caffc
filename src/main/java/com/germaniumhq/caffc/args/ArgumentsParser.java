package com.germaniumhq.caffc.args;

import com.germaniumhq.caffc.compiler.settings.BuildSettings;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArgumentsParser {
    public static BuildSettings parse(String[] args) {
        List<String> arguments = new LinkedList<>(Arrays.asList(args));
        BuildSettings buildConfig = new BuildSettings();

        System.out.println("Running with arguments: " + arguments);

        String projectConfigFile = getProjectConfigFile();
        if (projectConfigFile != null) {
            System.out.println("Project config file found: " + projectConfigFile);
            BuildSettings.readFromFile(buildConfig, projectConfigFile);
        }

        while (!arguments.isEmpty()) {
            String argument = arguments.remove(0);

            switch (argument) {
                case "--help":
                case "-h":
                        printHelp();
                        System.exit(0);
                        break;
                case "-v":
                case "--version":
                        printVersion();
                        System.exit(0);
                case "-o":
                case "--output":
                    buildConfig.setOutputFolder(arguments.remove(0));
                    break;
                case "-t":
                case "--templates":
                    buildConfig.setTemplatesFolder(arguments.remove(0));
                    break;
                case "-onefile":
                    buildConfig.setOneFile(arguments.remove(0));
                    break;
                default:
                    buildConfig.getInputSources().add(argument);
                    break;
            }
        }

        return buildConfig;
    }

    private static String getProjectConfigFile() {
        File file = new File("caffc.yaml");

        if (file.exists()) {
            return file.getAbsolutePath();
        }

        return null;
    }

    private static void printVersion() {
        System.out.println("caffc-native 0.0.6");
    }

    private static void printHelp() {
        System.out.println("caffc [options] [sources]");
        System.out.println();
        System.out.println("Compile the given sources.");
        System.out.println();
        System.out.println("Options:");
        System.out.println("-v/--version             show the current version");
        System.out.println("-o/--output              output folder (default: .)");
        System.out.println("-t/--templates           templates folder (default: .)");
        System.out.println("-onefile <file>          output all code to a single .c and .h file pair");
        System.out.println();
    }
}
