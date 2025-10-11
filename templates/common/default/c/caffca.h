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
    caffc_u8 _caffc_flags; /* FIXME: pack into the header pointer */
    caffc_i32 _caffc_field_count;
    caffc_ptr _caffc_data[];
} caffc_array_header;

caffc_bool caffc_is_array(caffc_ptr object);

#endif  /* ifndef __caffca_h */
