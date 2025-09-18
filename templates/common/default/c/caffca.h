#ifndef __caffca_h
#define __caffca_h

#include "caffcpt.h"
#include "caffco.h"

/**
 * object header for an array object (not regular object, not primitive)
 *
 * unlike regular objects, an array needs to know what types it allows
 * and how many items it contains, so the GC can use this information
 * when collecting the data.
 */
typedef struct {
    caffc_class_header* _caffc_class_header;
    caffc_class_header* _caffc_contained_class_header;
    u8 _caffc_flags; /* FIXME: pack into the header pointer */
    i32 _caffc_field_count;
    ptr _caffc_data[0];
} caffc_array_header;

bool caffc_is_array(ptr object);

#endif  /* ifndef __caffca_h */
