package com.germaniumhq.caffc.compiler.settings.debug;

import com.germaniumhq.caffc.compiler.settings.BuildSettings;

import java.util.Map;

public final class DebugSettings {
    public CLineMacro cLineMacro = CLineMacro.macro;
    public TraceLineRuntime traceLineRuntime = TraceLineRuntime.file_and_line;

    public void readFrom(Map<String, Object> config) {
        if (config.containsKey("c_line_macro")) {
            String value = config.get("c_line_macro").toString();
            cLineMacro = BuildSettings.parseEnum(CLineMacro.class, value, "c_line_macro");
        }

        if (config.containsKey("trace_line_runtime")) {
            String value = config.get("trace_line_runtime").toString();
            traceLineRuntime = BuildSettings.parseEnum(TraceLineRuntime.class, value, "trace_line_runtime");
        }
    }
}
