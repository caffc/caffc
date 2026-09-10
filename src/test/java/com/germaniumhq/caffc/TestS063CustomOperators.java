package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

import static com.germaniumhq.caffc.CodeAssertsStr.compileFullCaffcProgram;

public class TestS063CustomOperators {
    @Test
    public void testBinaryOperatorsLowerToMethods() {
        String code = compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[]{
                        new TestUnit("test.caffc",
                                """
                                        module main

                                        class Num {
                                          i32 value

                                          constructor(i32 value) {
                                            _this.value = value
                                          }

                                          add(Num other) -> Num {
                                            return new Num(_this.value + other.value)
                                          }

                                          substract(Num other) -> Num {
                                            return new Num(_this.value - other.value)
                                          }

                                          multiply(Num other) -> Num {
                                            return new Num(_this.value * other.value)
                                          }

                                          divide(Num other) -> Num {
                                            return new Num(_this.value / other.value)
                                          }

                                          pipe(Num other) -> Num {
                                            return new Num(_this.value | other.value)
                                          }
                                        }

                                        main() -> i32 {
                                          Num a = new Num(10)
                                          Num b = new Num(2)
                                          Num c = a + b
                                          Num d = a - b
                                          Num e = a * b
                                          Num f = a / b
                                          Num g = a | b
                                          return 0
                                        }
                                        """)
                });

        CodeAssertsStr.assertCodeContains(code, "main_Num_add(",
                "+ should lower to add()");
        CodeAssertsStr.assertCodeContains(code, "main_Num_substract(",
                "- should lower to substract()");
        CodeAssertsStr.assertCodeContains(code, "main_Num_multiply(",
                "* should lower to multiply()");
        CodeAssertsStr.assertCodeContains(code, "main_Num_divide(",
                "/ should lower to divide()");
        CodeAssertsStr.assertCodeContains(code, "main_Num_pipe(",
                "| should lower to pipe()");
    }

    @Test
    public void testCompoundAssignLowersToAllMethods() {
        String code = compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[]{
                        new TestUnit("test.caffc",
                                """
                                        module main

                                        class Num {
                                          i32 value

                                          constructor(i32 value) {
                                            _this.value = value
                                          }

                                          addAll(Num other) -> Num {
                                            _this.value = _this.value + other.value
                                            return _this
                                          }

                                          substractAll(Num other) -> Num {
                                            _this.value = _this.value - other.value
                                            return _this
                                          }

                                          multiplyAll(Num other) -> Num {
                                            _this.value = _this.value * other.value
                                            return _this
                                          }

                                          divideAll(Num other) -> Num {
                                            _this.value = _this.value / other.value
                                            return _this
                                          }

                                          pipeAll(Num other) -> Num {
                                            _this.value = _this.value | other.value
                                            return _this
                                          }
                                        }

                                        main() -> i32 {
                                          Num a = new Num(10)
                                          Num b = new Num(2)
                                          a += b
                                          a -= b
                                          a *= b
                                          a /= b
                                          a |= b
                                          return 0
                                        }
                                        """)
                });

        CodeAssertsStr.assertCodeContains(code, "main_Num_addAll(",
                "+= should lower to addAll()");
        CodeAssertsStr.assertCodeContains(code, "main_Num_substractAll(",
                "-= should lower to substractAll()");
        CodeAssertsStr.assertCodeContains(code, "main_Num_multiplyAll(",
                "*= should lower to multiplyAll()");
        CodeAssertsStr.assertCodeContains(code, "main_Num_divideAll(",
                "/= should lower to divideAll()");
        CodeAssertsStr.assertCodeContains(code, "main_Num_pipeAll(",
                "|= should lower to pipeAll()");
    }

    @Test
    public void testStringPlusUsesAdd() {
        String code = compileFullCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "test.caffc",
                new TestUnit[]{
                        new TestUnit("test.caffc",
                                """
                                        module main

                                        main() -> i32 {
                                          str a = "hello"
                                          str b = " world"
                                          str c = a + b
                                          return 0
                                        }
                                        """)
                });

        CodeAssertsStr.assertCodeContains(code, "caffc_str_add(",
                "str + str should lower to str.add()");
    }

    @Test
    public void testPrimitivesStillUseCOperators() {
        String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[]{
                        new TestUnit("a/a.caffc",
                                """
                                        module main

                                        main() -> i32 {
                                          i32 x = 3
                                          i32 y = 2
                                          i32 z = x + y
                                          z += y
                                          return 0
                                        }
                                        """)
                });

        CodeAssertsStr.assertCodeContains(code, "x + y",
                "primitive + must stay a C operator");
        CodeAssertsStr.assertCodeContains(code, "z = z + y",
                "primitive += must stay a C operator");
    }
}
