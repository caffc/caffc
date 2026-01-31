package com.germaniumhq.caffc;

import org.junit.jupiter.api.Test;

/**
 * This just checks if the code generation works, there's a full integration
 * test to actually check listing of the folder.
 */
public class TestS200IntegrationGenericsFor {
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
class obj {}
class obj_arr<T> {
  get(i32 i) -> T {}
  set(i32 i, T value) {}
  size() -> i32 {}
}

class File {
  str name

  constructor(str name) {
    _this.name = name
  }
}

class ArrayList<T> {
  T[] items
  i32 length

  constructor() {
    _this.items = new T[8]
  }

  /**
   * Add an item into the list
   */
  add(T item) {
    // if we're already at full capacity, we need to resize the array
    if _this.length >= _this.items.size() {
      T[] new_items = new T[_this.items.size() + 8] // no multiplication yet lol

      for i32 i = 0; i < items.size(); i = i + 1 {
        new_items[i] = _this.items[i]
      }

      _this.items = new_items
    }

    _this.items[_this.length] = item
    _this.length = _this.length + 1
  }

  /**
   * Gets an item from the list by its index.
   */
  get(i32 index) -> T {
    return _this.items[index]
  }
}

list_files(str path) -> ArrayList<File> {
  ArrayList<File> result = new ArrayList()
  result.add(new File(""))
}

""")
                }
        );

        System.out.println(code);
    }
}
