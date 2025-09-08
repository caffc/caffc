# CAFFC

CaffC is Caffeinated C.

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

## Classes

CaffC has objects, however objects don't have inheritance, only interfaces do.
This avoids having abstract classes, forces composition, and eliminates all
problems related to `super()` like calls.

```caffc
interface Rectangle {
  i32 area()
}

class Square implements Shape, Rectangle {
  i32 _x, _y
  i32 _width

  i32 area() {
    return _this._width * _this._width
  }
} 
```

There's three scopes for variables and methods:

* module-private (the default),
* private - if prefixed with `_` in the name (i.e. `_user_age`). To access
  class members use `_this`.
* public - if the item is tagged with the `#public` tag.

## Garbage Collection

Even if this transpiles to C, the memory allocated is managed.
