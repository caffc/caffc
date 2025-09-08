
struct caffc_lang_type_header {
    i32 _type_id;
    i32 _pointer_fields;  // -1 == array?
    i32 _size;            // sizeof() object
}

struct caffc_io_InputStream_type {
    i32 _type_id;
    i32 _pointer_fields;  // -1 == array?
    i32 _size;            // sizeof() object
};

struct caffc_io_InputStream_vt {
    u8 read();
    u8 seek();
}

struct caffc_io_InputStream {
    ptr _caffc_io_InputStream;
    ptr _caffc_move_to;
};


struct caffc_io_Closeable {
    ptr _caffc_io_Closeable;
    ptr _caffc_move_to;
}


struct caffc_io_FileInputStream {
    ptr _caffc_io_FileInputStream;
    int32 _this_offset; // 0
    ptr _caffc_move_to;  // used by GC
    ptr _caffc_io_InputStream;
    int32 _this_offset; // 20 = sizeof(ptr) * 2 + sizeof(_this_offset)
    ptr _caffc_io_Closeable;
    int32 _this_offset; // 32 = sizeof(ptr) * 2
}

ptr caffc_io_FileInputStream_cast(ptr _this, ptr target_type) {
    if (!target_type) {
        // throw exception
    }

    if (target_type == caffc_io_InputStream_type) {

    } else if (target_type == caffc_io_InputStream_type) {
    } else {
        // throw exception;
    }
}
