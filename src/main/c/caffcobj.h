#ifdef __cplusplus
extern "C" {
#endif


struct _caffc_type_header {
  i32 _type_id;
  i32 _pointer_fields; // count of the pointer fields, info for GC
  i32 _size;           // pointers + extra fields of the object
  // fields
  // FIXME: extra metadata
}

struct _caffc_array_header {
    ptr _caffc_type;
    ptr _caffc_move_to;  // use by the garbage collector
    i64 len;            // length of the array
    // ptr pointers[];
}

struct _caffc_object_header {
    ptr _caffc_type;
    ptr _caffc_move_to;  // used by the gc
}

class Point {
  startPoint, endPoint Point
  x, y i32
}

struct io_data_Point {
    ptr _caffc_type;
    ptr _caffc_move_to;  // use by the garbage collector

    // data fields to other CAFFC objects come first
    // the actual count of pointers is in the _caffc_type_header
    ptr startPoint;
    ptr endPoint;

    // extra fields
    i32 x;
    i32 y;
};

set_point(extend Point, i32 x, i32 y) {
    _this.x = x
    throw Exception("ded")
    _this.y = y
}

void io_data_Point_set_point(io_data_Point* _this, int x, int y) {
    __caffc_frame_start("set_point", 13);
    __caffc_frame_line(14);
    _this->x = x;
    __caffc_frame_line(15);
    __caffc_ref s = __caffc_new(caffc_lang_str)
    __caffc_lang_str_constructor("ded")
    __caffc_new(caffc_lang_Exception)
    __caffc_lang_Exception_constructor(s)
    __caffc_frame_line(16);
    _this.y = y;
_caffc_return:
    _caffc_frame_clean();
    return;
}

_caffc_type(CAFFC_IO_STRING)->vt->get(this, 3, 4);

u8 x = ((caffc_lang_string*)this->_caffc_type->vt)->get(this, 3);

struct caffc_lang_string_type {
    i32 pointerFields = 1; // buffer
    i32 size = 12; // 8 pointer, 4 size
    i32 fields = 2;
    i32 methods = 1;
} x = {
    1,  /* pointerFields */
    12, /* size */
    2,  /* fields */
    1,  /* methods */
};

struct caffc_io_string_vt {
    u8 get*(i32 index);
}

u8 caffc_lang_string_get(io_string* _this, i32 index) {
  if (!_this) {
    // exception
  }

  switch (_this->_caffc_type->_type_id) {
    case 1:  // CAFFC_LANG_STRING
      return caffc_lang_string_get(_this, index);
    default:
      // exception
  }
}

#ifdef __cplusplus
}
#endif
