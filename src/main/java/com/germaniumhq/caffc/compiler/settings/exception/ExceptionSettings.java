package com.germaniumhq.caffc.compiler.settings.exception;

import com.germaniumhq.caffc.compiler.settings.CaffcFeature;

import java.util.Map;

/**
 * Settings for the `exception` profile.
 */
public class ExceptionSettings implements CaffcFeature {
    @Override
    public String implName() {
        return "default";
    }

    @Override
    public void readFrom(Map<String, Object> config) {
    }
}
