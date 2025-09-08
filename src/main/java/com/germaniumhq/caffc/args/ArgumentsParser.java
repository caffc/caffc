package com.germaniumhq.caffc.args;

import com.germaniumhq.caffc.compiler.BuildConfig;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ArgumentsParser {
    public static BuildConfig parse(String[] args) {
        List<String> arguments = new LinkedList<>(Arrays.asList(args));
        BuildConfig buildConfig = new BuildConfig();

        System.out.println("Running with arguments: " + arguments);

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
                default:
                    buildConfig.getInputSources().add(argument);
                    break;
            }
        }

        return buildConfig;
    }

    private static void printVersion() {
        System.out.println("caffc-native 1.0.0");
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
        System.out.println();
    }
}
