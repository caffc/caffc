package com.germaniumhq.caffc.output.filters;

import com.germaniumhq.caffc.compiler.model.NativeBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmBlock;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmComment;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmIfJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmIfZJmp;
import com.germaniumhq.caffc.compiler.model.asm.opc.AsmLabel;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.List;
import java.util.Map;

/**
 * Decides if a semicolon should be outputted or not, depending
 * on the instruction type.
 */
public class FilterSemicolon implements Filter {
    @Override
    public Object apply(Object o, Map<String, Object> map, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int i) throws PebbleException {
        if (o instanceof AsmBlock) {
            return "";
        }

        if (o instanceof NativeBlock) {
            return "";
        }

        if (o instanceof AsmLabel) {
            return "";
        }

        if (o instanceof AsmIfZJmp) {
            return "";
        }

        if (o instanceof AsmIfJmp) {
            return "";
        }

        if (o instanceof AsmComment) {
            return "";
        }

        return ";";
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
