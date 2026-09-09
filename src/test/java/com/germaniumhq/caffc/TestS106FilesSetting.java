package com.germaniumhq.caffc;

import com.germaniumhq.caffc.compiler.model.source.SourceLocation;
import com.germaniumhq.caffc.compiler.settings.FilesSetting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class TestS106FilesSetting {
    @Test
    public void globIncludesAndExcludes() {
        FilesSetting files = new FilesSetting();
        files.readFrom(Map.of(
                "includes", List.of(
                        "glob(\"generated/*.caffc\")",
                        "glob('extra/**/*.caffc')"
                ),
                "excludes", List.of("glob(\"generated/*WIP*\")")
        ), SourceLocation.UNKNOWN);

        Assertions.assertTrue(files.contains("generated/CodePage8859_2.caffc"));
        Assertions.assertFalse(files.contains("generated/CodePageWIP.caffc"));
        Assertions.assertTrue(files.contains("extra/nested/x.caffc"));
        Assertions.assertFalse(files.contains("other/x.caffc"));
    }

    @Test
    public void plainPatternAccepted() {
        FilesSetting files = new FilesSetting();
        files.readFrom(Map.of(
                "includes", List.of("generated/CodePage8859_2.caffc")
        ), SourceLocation.UNKNOWN);

        Assertions.assertTrue(files.contains("generated/CodePage8859_2.caffc"));
        Assertions.assertFalse(files.contains("generated/other.caffc"));
    }

    @Test
    public void parseGlobEntry() {
        Assertions.assertEquals(
                "generated/*.caffc",
                FilesSetting.parseGlobEntry("glob(\"generated/*.caffc\")", "includes", SourceLocation.UNKNOWN));
        Assertions.assertEquals(
                "a.b",
                FilesSetting.parseGlobEntry("a.b", "includes", SourceLocation.UNKNOWN));
    }
}
