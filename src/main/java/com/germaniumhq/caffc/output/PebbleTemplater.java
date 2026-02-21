package com.germaniumhq.caffc.output;

import com.germaniumhq.caffc.compiler.model.CompilationUnit;
import com.germaniumhq.caffc.compiler.model.Module;
import com.germaniumhq.caffc.compiler.model.Program;
import com.germaniumhq.caffc.compiler.model.source.HasSourceLocation;
import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.EvaluationContextImpl;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * A utility class for rendering templates using the Pebble templating engine.
 * This class provides methods to render templates to strings or writers,
 * manage a cache of compiled templates, and create render contexts from
 * various types of root contexts.
 */
public class PebbleTemplater {
    /**
     * The PebbleEngine instance used for template rendering.
     */
    private final PebbleEngine engine;

    /**
     * A cache of compiled Pebble templates to improve performance.
     */
    private Map<String, PebbleTemplate> templateCache = new HashMap<>();

    /**
     * A singleton instance of PebbleTemplater.
     */
    public static PebbleTemplater INSTANCE = new PebbleTemplater();

    /**
     * Private constructor to enforce singleton pattern.
     * Initializes the PebbleEngine with specific configurations.
     */
    private PebbleTemplater() {
        this.engine = new PebbleEngine.Builder()
                .strictVariables(true)
                .autoEscaping(false)
                .newLineTrimming(false)
                .extension(new CaffcPebblesExtension())
                .build();
    }

    /**
     * Renders a template to a string using the provided render context.
     *
     * @param template      The template name or content to render.
     * @param renderContext The context data to use during rendering.
     * @return The rendered template as a string.
     */
    public String renderToString(String template, Map<String, Object> renderContext) {
        StringWriter writer = new StringWriter();
        renderTemplate(writer, template, renderContext);

        return writer.getBuffer().toString();
    }

    /**
     * Renders a template to a writer using the provided render context.
     *
     * @param writer        The writer to which the rendered template will be written.
     * @param template      The template name or content to render.
     * @param renderContext The context data to use during rendering.
     */
    public void renderTemplate(Writer writer, String template, Map<String, Object> renderContext) {
        try {
            PebbleTemplate t = getTemplate(template);
            t.evaluate(writer, renderContext);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format(
                    "Unable to render %s on renderContext: %s. Source: %s. ctx: %s.",
                    template, renderContext, getAstPosition(renderContext), renderContext.get("ctx")
            ));
        } catch (RuntimeException e) {
            throw new RuntimeException(
                    String.format("Unable to render %s. Source: %s. ctx: %s.",
                            template,
                            getAstPosition(renderContext),
                            renderContext.get("ctx")), e);
        }
    }

    private String getAstPosition(Map<String, Object> renderContext) {
        if (renderContext == null) {
            return "<unknown>";
        }

        Object ctx = renderContext.get("ctx");
        if (ctx == null) {
            return "<unknown>";
        }

        if (!(ctx instanceof HasSourceLocation)) {
            return "<unknown>";
        }

        SourceLocation sourceLocation = ((HasSourceLocation) ctx).getSourceLocation();
        return String.format("%s:%d:%d", sourceLocation.getFilePath(), sourceLocation.getLineNumber(), sourceLocation.getColumnNumber());
    }

    /**
     * Retrieves a compiled PebbleTemplate from the cache or compiles it if not present.
     *
     * @param template The template name or content to retrieve or compile.
     * @return The compiled PebbleTemplate.
     */
    private PebbleTemplate getTemplate(String template) {
        return templateCache.computeIfAbsent(template, engine::getTemplate);
    }

    /**
     * Creates a render context map from a root context object.
     * The root context can be an instance of Program, Module, or CompilationUnit.
     *
     * @param rootContext The root context object.
     * @return A map containing the render context data.
     */
    public static Map<String, Object> createRenderContext(Object rootContext) {
        Map<String, Object> result = new HashMap<>();

        result.put("ctx", rootContext);

        if (rootContext instanceof Program program) {
            result.put("program", program);
        } else if (rootContext instanceof Module module) {
            result.put("module", rootContext);
            result.put("program", module.program);
        } else if (rootContext instanceof CompilationUnit compilationUnit) {
            result.put("unit", compilationUnit);
            result.put("module", compilationUnit.module);
            result.put("program", compilationUnit.module.program);
        } else {
            throw new IllegalArgumentException("Unsupported root context type: " + rootContext);
        }

        return result;
    }

    /**
     * Creates a render context map from a root context object and a parent render context.
     * Extracts additional data from the parent render context's scope chain.
     *
     * @param rootContext         The root context object.
     * @param parentRenderContext The parent render context.
     * @return A map containing the render context data.
     */
    public static Map<String, Object> createRenderContext(Object rootContext, EvaluationContext parentRenderContext) {
        Map<String, Object> result = new HashMap<>();

        result.put("ctx", rootContext);

        Object config = ((EvaluationContextImpl) parentRenderContext).getScopeChain().get("config");
        result.put("config", config);

        Object module = ((EvaluationContextImpl) parentRenderContext).getScopeChain().get("module");
        result.put("module", module);

        Object program = ((EvaluationContextImpl) parentRenderContext).getScopeChain().get("program");
        result.put("program", program);

        return result;
    }
}
