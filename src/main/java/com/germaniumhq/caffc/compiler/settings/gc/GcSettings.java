package com.germaniumhq.caffc.compiler.settings.gc;

import com.germaniumhq.caffc.compiler.settings.CaffcFeature;

import java.util.Map;

/**
 * This is just a selector. Depending on the "impl" a different class will be selected,
 * with potentially completely different settings.
 *
 * i.e. later we'll have a RefCountingSettings implementation with completely different
 *      settings.
 */
public interface GcSettings extends CaffcFeature {
    static GcSettings createFromName(String name) {
        if (name == null || name.equals("default")) {
            return new GcSettingsDefault();
        }
        throw new IllegalArgumentException("Unknown GC implementation: " + name);
    }

    default void readFrom(Map<String, Object> config) {
    }
}
