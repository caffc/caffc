package com.germaniumhq.caffc.compiler.settings;

import com.germaniumhq.caffc.compiler.settings.gc.GcSettings;

import java.util.Map;

public interface CaffcFeature {
    /**
     * The name of the implementation specified in the `caffc.yaml`.
     *
     * The folder where the files of the feature reside is in:
     *
     * templates/{packageName}/{implName}
     *
     * NOTE: This is only used to keep track in a specific feature
     *       implementation what settings are there available.
     */
    String implName();

    /**
     * Read settings from the YAML config (without the "impl" key, which is used
     * by the factory to instantiate the correct class).
     */
    default void readFrom(Map<String, Object> config) {
    }
}
