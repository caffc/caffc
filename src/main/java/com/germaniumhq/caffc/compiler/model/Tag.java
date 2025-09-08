package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.generated.caffcParser;

import java.util.HashMap;
import java.util.Map;

public class Tag {
    public String name;
    public Map<String, String> values = new HashMap<>();

    public static Tag fromAntlr(CompilationUnit unit, AstItem owner, caffcParser.TagContext tag) {
        Tag result = new Tag();

        result.name = tag.fqdn().getText();
        for (caffcParser.TagParamContext param: tag.tagParam()) {
            String keyName = "value";

            if (param.ID() != null) {
                keyName = param.ID().getText();
            }

            String value = param.constExpression().getText();

            // remove the quotes
            if (value.startsWith("\"") && value.endsWith("\"") ||
                    value.startsWith("'") && value.endsWith("'")) {
                value = value.substring(1, value.length() - 1);
            }

            result.values.put(keyName, value);
        }

        return result;
    }

    public String get(String name) {
        return values.get(name);
    }
}
