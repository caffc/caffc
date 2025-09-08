
#define ptr void*
#define i32 int

/*
 * CLASS header
 */
typedef struct {
    const char * _fqdn;
    i32 _field_count;
    i32 _size;
} caffc_class_header;

/*
 * Object header
 */
typedef struct {
    caffc_class_header* class_header;
    union {
        u8 is_marked;
        ptr move_to;
    }
} caffc_object_header;

/*
 * A random object to be casted
 */
typedef struct {
    caffc_object_header _caffc_header;
} caffc_object_template;

/**
 * string
 */
typedef struct {
    caffc_object_header caffc_header;
    ptr buffer;
    i32 len;  // string byte length
} caffc_string;

caffc_class_header caffc_string_type = {
    /* _fqdn */ "caffc.str",
    /* _field_count */ 0,
    /* _size */ sizeof(caffc_string)
};

