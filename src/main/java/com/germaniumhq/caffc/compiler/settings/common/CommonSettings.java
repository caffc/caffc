package com.germaniumhq.caffc.compiler.settings.common;

import com.germaniumhq.caffc.compiler.settings.CaffcFeature;

import java.util.Map;

/**
 * Settings for the common profile.
 * FIXME: rename the `common` profile to `core`.
 */
public final class CommonSettings implements CaffcFeature {
    @Override
    public String implName() {
        return "default";
    }

    @Override
    public void readFrom(Map<String, Object> config) {
    }
}
