package com.germaniumhq.caffc.compiler.settings.gc;

import java.util.Map;

/**
 * The default GC. The benefit of this one is that it's implemented
 * in pure C, without any synchronizations. Should compile everywhere
 * libc is available (relies on libC).
 */
public final class GcSettingsDefault implements GcSettings {
    /**
     * The amount of allocated memory before triggering a full gc.
     * The value is in bytes and it will be inserted as is as a macro
     * inside parenthesis.
     */
    public String memoryTrigger;

    @Override
    public String implName() {
        return "default";
    }

    @Override
    public void readFrom(Map<String, Object> config) {
        if (config.containsKey("memory_trigger")) {
            memoryTrigger = config.get("memory_trigger").toString();
        }
    }

    public void setMemoryTrigger(String memoryTrigger) {
        this.memoryTrigger = memoryTrigger;
    }

    public String getMemoryTrigger() {
        return memoryTrigger;
    }
}
