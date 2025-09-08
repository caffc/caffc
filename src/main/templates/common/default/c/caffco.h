#ifndef __caffco_h
#define __caffco_h

#include "caffcpt.h"  /* primitive types */
#include "caffcc.h"   /* caffc class      */

/**
 * An object is marked as used in the first pass, so it won't be
 */
#define CAFFC_OBJECT_FLAGS_GC_MARKED 0x01
#define CAFFC_OBJECT_FLAGS_GC_STATIC 0x02

/*
 * object header for a normal object (not array, not primitive)
 */
typedef struct {
    caffc_class_header* _caffc_class_header;
    u8 _caffc_flags; /* FIXME: pack into the header pointer */
    ptr _caffc_data[0];
} caffc_object_header;

#endif  /* ifndef __caffco_o */
