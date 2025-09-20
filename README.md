# CAFFC

CaffC is Caffeinated C.

## Introduction

CaffC is a high-level language like Java, or C#. The idea is to have a high-level language,
but with low-level access when needed via `native` blocks. The language should be easy to
learn and understand, and the code written in it comprehensible.

## Features

* CaffC code transpiles to _pure C_
* classes _without inheritance_
* interfaces
* _no threads_
* garbage collected
* trivial C integration via `native` blocks
* exceptions (TBD)
* generics
* native UTF-8 string support (TBD)
* fast compilation times
* tags (annotations) (WIP)
* decorators (TBD)
* multi-returns (TBD)

## Primitive Types

Numeric types are represented in how many bits they have; `unsigned`
numbers are prefixed by `u`, while signed are just `i`.

So we have: `u8`, `i8`, `u16`, `i16`, `u32`, `i32`, `u64` and `i64` for
integer numbers.

Floating point numbers are `f32` and `f64` respectively.

CaffC offers also a `ptr` type, that has its size unspecified, and is
actually a `void*`. This exists only to allow classes to keep a reference
to a memory address somewhere. `ptr` references are not managed by the GC.

To specify a type for a constant, suffix it with its type after `_`:

```
u32 x = 32_u32
```

## Functions

CaffC has functions. The notation is slightly different from C:

```caffc
computeArea(u32 width, u32 height) -> u32 {
  return width * height
}
```

If the function is void, the return can be omitted:

```cafc
log(str what) {
  // ...
}
```

## Classes

CaffC has classes and objects. They work as you expect

```caffc
class Rectangle {
  // if fields exist, they must be at the beginning of the class
  u32 width, height

  constructor(u32 width, u32 height) {
    _this.width = width
    _this.height = height
  }

  area() -> u32 {
    return _this.width * _this.height
  }

  _perimeter() -> u32 {
    return _this.width * 2 + _this.height * 2
  }
}

createRectangle() {
  Rectangle r = new Rectangle(20, 10)
  u32 area = r.area()
}
```

There's three scopes for variables and methods:

* module-private (the default),
* private - if prefixed with `_` in the name (i.e. the `_perimeter` method is private),
* public - if the item is tagged with the `#public` tag.

A major difference to other programming languages is that _classes don't have inheritance_.
You cannot extend another class. This avoids having abstract classes, forces composition,
and eliminates all problems related to `super()` like calls.

Another difference, is that functions and methods, true to their C counterpart, _don't
have overloading_. In the future, CaffC will have default parameters, and calls with
named parameters to mitigate this.

In methods `_this` is available to access the current class instance.

## Generics

Both classes and functions support generics.

```caffc
class A<T> {
  T item  // T is `obj` in C code
}

f() {
  A<Potato> p = new A<Potato>()  // a.item can be only assigned to Potato instances
  A<Vase> v = new A<Vase>()      // a.item can be only assigned to Vase instances
}
```

Generics are only checked at compilation, not in the generated C code. That means both
`p` and `v` from the previous example are the same type.

Generics can be _restricted_. When they are, the actual C implementation will use the
restricted type:

```caffc
interface Item {
  getX() -> u32
}

class A<T is Item> {
  T item  // T is `Item` in C code

  getItemX() {
    return _this.item.getX()  // this works, since _this.item is an Item
  }
}
```

This wouldn't otherwise work in the `class A<T> {}` definition.

For functions it's the same:

```caffc
x<T is Item>(T t) -> T {
  return t
}

f() {
  Vase vase = new Vase()
  Vase sameVase = x<Vase>(vase)
}
```

## Interfaces

CaffC has interfaces. Interfaces _can_ extend other interfaces.

```caffc
interface Item {
  getName() -> str
}

class Vase implements Item {
  getName() -> str {
    return "vase"
  }
}

class Potato implements Item {
  getName() -> str {
    return "potato"
  }
}

items() {
  Item v = new Vase()
  Item p = new Potato()

  print(v.getName())  // prints "vase"
  v = p
  print(v.getName())  // prints "potato"
}
```

## Arrays

CaffC supports both primitive arrays, and object arrays. Object arrays are of
course garbage collected. Multidimensional arrays are also supported. Arrays
cannot be resized after creation.

There's plans to have collection classes for the big three: dict, set and list.

```caffc
main() {
  Swag[][] arr = new Swag[2][2]

  arr[0][0] = new Swag(0)
  arr[0][1] = new Swag(1)
  arr[1][0] = new Swag(2)
  arr[1][1] = new Swag(3)

  print_value(arr[0][1].data) // expect 1
  arr[0][1] = arr[1][0]
  print_value(arr[0][1].data) // expect 2
}
```

## Garbage Collection

Even if this transpiles to C, the memory allocated is managed. All objects, and
arrays are garbage collected. Local variables, and parameters, are all handled
by the GC.

There shouldn't be any memory leaks, even if the program exits randomly with `exit(n)`.

```caffc
main() -> i32 {
  str[] x = new str[2]  // all these are GC now, even if we exit(0) somewhere

  x[0] = "abc"
  x[1] = "cde"

  print_string(x[0])
  print_string(x[1])

  return 0
}

native { // this will be generated in the future
  int main(int argc, char** argv) {
    atexit(caffc_done);
    caffc_init();

    return main_main();
  }
}
```

## native Blocks

Anything passed in `native` blocks, gets sent to the C code _as is_, without modifications.

Function parameters, local variables, the `_this` pointer and its fields, are also available
in the `native` code with the same names. This makes interfacing with the underlying system
trivially simple.

For example, this is how the `print` is implemented for simple strings:

```caffc
native {
  #include <stdio.h>
}

print(str what) {
  native {
    printf("%s\n", what->_caffc_data);
  }
}
```

Here's a more complicated example:

```caffc
native {
  #include <something.h>
}

class A {
  u32 x

  sum(u32 y) -> u32 {
    u32 result

    native {
      // object fields are also available in the native code
      result = _this->x + y;
    }

    return result
  }
}
```

Native blocks should seldomly be used. The idea is to stay as much as possible in the
high-level language.
