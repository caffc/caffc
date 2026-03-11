#ifndef __caffco_h
#define __caffco_h

#include "caffcpt.h"  /* primitive types */
#include "caffcc.h"   /* caffc class      */

/**
 * The first bit of the type_id keeps if the object we're seeing
 * is an array or regular object. This will help the GC figure
 * out where to look for the field count.
 *
 * The type_id holds as its first bit the type of the object:
 * regular object is 0, 1 this is an array.
 */
#define CAFFC_OBJECT_TYPE_ID_FLAG_MASK   0x80000000
#define CAFFC_OBJECT_TYPE_ID_FLAG_OBJECT 0x00000000
#define CAFFC_OBJECT_TYPE_ID_FLAG_ARRAY  0x80000000

#define CAFFC_OBJECT_TYPE_ID_TYPE_MASK   0x70000000

/**
 * An object is marked as used in the first pass, so it won't be
 * deleted in the sweep phase.
 * FIXME: I'm not sure I need the static, since static objects
 *        aren't in the allocation list.
 */
#define CAFFC_OBJECT_FLAGS_GC_MARKED 0x01
#define CAFFC_OBJECT_FLAGS_GC_STATIC 0x02

/*
 * object header for a normal object (not array, not primitive)
 */
typedef struct {
    caffc_u32 _caffc_type_id; // type ID of the object.
    caffc_u8 _caffc_flags; /* FIXME: pack into the type_id? */
    caffc_ptr _caffc_data[];
} caffc_object_header;

/**
 * Sets the bytes at the given address to 0.
 */
caffc_void caffc_bzero(caffc_ptr p, caffc_u32 size);

#endif  /* ifndef __caffco_o */
