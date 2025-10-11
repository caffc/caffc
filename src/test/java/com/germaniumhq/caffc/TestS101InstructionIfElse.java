package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

public class TestS101InstructionIfElse {
    @Test
    public void testIfElseGeneration() {
        String code = CodeAsserts.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
            """
                    module main

                    test() -> i32 {
                      i32 x = 0
                      
                      if x == 3 {
                        x = 4
                      } else {
                        x = 5
                      }
                    }
                    """)
                }
        );

        CodeAsserts.assertCodeContains(code,
                """
                  if (x == 3) {
                x = 4;
                } else {
                x = 5;
                }
                """,
                "generating an if/else should work");
    }

    @Test
    public void testIfContinueGeneration() {
        String code = CodeAsserts.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                        module main
                    
                                        test(){
                                          for i32 i = 0; i < 3; i += 1 {
                                            if i == 1 continue
                                            if i == 2 {
                                              continue
                                            }
                                          }
                                        }
                                        """)
                }
        );

        CodeAsserts.assertCodeContains(code,
                """
i = 0;
while (i < 3) {
if (i == 1) {
continue;
}

if (i == 2) {
continue;
}

i += 1;
}
                """,
                "generating an if that has a simple continue should work");
    }

    @Test
    public void testIfBreakGeneration() {
        String code = CodeAsserts.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                        module main
                    
                                        test(){
                                          for i32 i = 0; i < 3; i += 1 {
                                            if i == 1 break
                                            if i == 2 {
                                              break
                                            }
                                          }
                                        }
                                        """)
                }
        );

        CodeAsserts.assertCodeContains(code,
                """
i = 0;
while (i < 3) {
if (i == 1) {
break;
}

if (i == 2) {
break;
}

i += 1;
}
                """,
                "generating an if that has a simple break should work");
    }

    @Test
    public void testIfReturnGeneration() {
        String code = CodeAsserts.compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                        new TestUnit("a/a.caffc",
                                """
                                        module main
                    
                                        test(){
                                          for i32 i = 0; i < 3; i += 1 {
                                            if i == 1 return
                                            if i == 2 {
                                              return
                                            }
                                          }
                                        }
                                        """)
                }
        );

        CodeAsserts.assertCodeContains(code,
"""
i = 0;
while (i < 3) {
if (i == 1) {

_caffc_stack_frame_unregister(caffc_null);
return;
}

if (i == 2) {

_caffc_stack_frame_unregister(caffc_null);
return;
}

i += 1;
}
""",
                "generating an if that has a simple return should work");
    }
}
