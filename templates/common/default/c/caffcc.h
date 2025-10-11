#ifndef __caffcc_h
#define __caffcc_h

/*
 * class header for objects (all except primitives)
 */
typedef struct {
    /**
     * Fully qualified domain name of the class, stored as a nul terminated
     * string constant.
     */
    caffc_ptr fqdn;

    /**
     * How many fields come after the header that the garbage collector
     * should take care of.
     *
     * NOTE: This is not used in object arrays, arrays have their own field count.
     */
    caffc_i32 field_count;
} caffc_class_header;

/*
 * Primitives also have a class type since they can be included
 * in arrays, and then they're not collected by the gc
 */
extern caffc_class_header caffc_bool_class;
extern caffc_class_header caffc_u8_class;
extern caffc_class_header caffc_i8_class;
extern caffc_class_header caffc_u16_class;
extern caffc_class_header caffc_i16_class;
extern caffc_class_header caffc_u32_class;
extern caffc_class_header caffc_i32_class;
extern caffc_class_header caffc_u64_class;
extern caffc_class_header caffc_i64_class;
extern caffc_class_header caffc_f32_class;
extern caffc_class_header caffc_f64_class;
extern caffc_class_header caffc_ptr_class;

#endif  /* ifndef __caffcc_h */
