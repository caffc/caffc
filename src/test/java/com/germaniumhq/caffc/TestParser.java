package com.germaniumhq.caffc;

import com.germaniumhq.caffc.generated.caffcLexer;
import com.germaniumhq.caffc.generated.caffcParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestParser {
    @Test
    public void testNativeParsing() throws IOException {
        caffcLexer lexer = new caffcLexer(new ANTLRInputStream(getClass().getResourceAsStream("/native.caffc")));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        caffcParser parser = new caffcParser(tokens);

        parser.compilationUnit();
    }
}
