#ifndef __caffco_h
#define __caffco_h

#include "caffcpt.h"  /* primitive types */

/**
 * The first bit of the type_id keeps if the object we're seeing
 * is an array or regular object. This will help the GC figure
 * out where to look for the field count.
 *
 * The type_id holds as its first bit the type of the object:
 * regular object is 0, 1 this is an array.
 *
 * // FIXME: this should be packed in the type_id
 */
#define CAFFC_OBJECT_TYPE_ID_FLAG_MASK   0x80
#define CAFFC_OBJECT_FLAGS_ARRAY  0x80
#define CAFFC_OBJECT_FLAGS_GC_MARKED 0x40

#define CAFFC_BIT_SET(v, bitval) v = v | (bitval)
#define CAFFC_BIT_CLEAR(v, bitval) v = v & ~(bitval)
#define CAFFC_BIT_GET(v, bitval) (v & bitval)

/**
 * An object is marked as used in the first pass, so it won't be
 * deleted in the sweep phase.
 * FIXME: I'm not sure I need the static, since static objects
 *        aren't in the allocation list.
 */

/*
 * object header for a normal object (not array, not primitive)
 */
typedef struct {
    caffc_u32 _caffc_type_id; /* type ID of the object. */
    caffc_u8 _caffc_flags; /* FIXME: pack into the type_id */
    caffc_ptr _caffc_data[];
} caffc_object_header;

/**
 * Sets the bytes at the given address to 0.
 */
caffc_void caffc_bzero(caffc_ptr p, caffc_u32 size);

/**
 * Global exception variable. When an exception is thrown, it's stored here.
 * After each function call, this should be checked and if not NULL, jump
 * to the exception handler.
 */
extern caffc_ptr _caffc_exception;

/**
 * Checks if an object is an instance of a type.
 */
caffc_bool caffc_instanceof_check(caffc_ptr o, caffc_u32 type_id);

#endif  /* ifndef __caffco_o */
