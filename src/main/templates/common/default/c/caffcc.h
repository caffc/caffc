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
    ptr fqdn;

    /**
     * How many fields come after the header that the garbage collector
     * should take care of.
     *
     * NOTE: This is not used in object arrays, arrays have their own field count.
     */
    i32 field_count;
} caffc_class_header;

/*
 * Primitives also have a class type since they can be included
 * in arrays, and then they're not collected by the gc
 */
extern caffc_class_header ccaf_u8;
extern caffc_class_header ccaf_i8;
extern caffc_class_header ccaf_u16;
extern caffc_class_header ccaf_i16;
extern caffc_class_header ccaf_u32;
extern caffc_class_header ccaf_i32;
extern caffc_class_header ccaf_u64;
extern caffc_class_header ccaf_i64;
extern caffc_class_header ccaf_f32;
extern caffc_class_header ccaf_f64;
extern caffc_class_header ccaf_ptr;

#endif  /* ifndef __caffcc_h */
