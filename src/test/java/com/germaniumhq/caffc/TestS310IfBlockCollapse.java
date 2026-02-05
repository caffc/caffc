package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

/**
 * This just checks if the code generation works, there's a full integration
 * test to actually check listing of the folder.
 */
public class TestS310IfBlockCollapse {
    @Test
    public void testBasicArrayListImplementation() {
        String code = CodeAssertsStr.compileCaffcProgram(
                "caffc/template/c/compilation_unit_c.peb",
                "a/a.caffc",
                new TestUnit[] {
                    new TestUnit("a/a.caffc",
            """
module caffc

class str {}
interface obj {}
class obj_arr<T> {
  get(i32 i) -> T {}
  set(i32 i, T value) {}
  size() -> i32 {}
}

test() {
  u32 x, y, z
  
  if (x == y) {
    y = x + z
  } else {
    z = x + y
  }
  
  x = y + z
}
""")
                }
        );

        System.out.println(code);
    }
}
