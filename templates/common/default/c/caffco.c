#include "caffco.h"

/**
 * Checks if a given object is an instance of a type.
 */
caffc_bool caffc_instanceof_check(caffc_ptr o, caffc_u32 ancestor_type_id) {
    caffc_object_header* header;
    caffc_u32 i;
    caffc_class_inheritance_data* inheritance_data;

    /* null pointers can't be the instanceof anything */
    if (!o) {
        return caffc_false;
    }

    if (ancestor_type_id == OBJECT_TYPE_CAFFC_OBJ) {
        return caffc_true;
    }

    header = (caffc_object_header*) o;

    /* check ancestor_type_id bounds? */
    inheritance_data = _caffc_class_headers[header->_caffc_type_id].inheritance_data;

    for (i = 0; i < inheritance_data.count; i++) {
        if (inheritance_data.implemented_type_ids[i] == ancestor_type_id) {
            return caffc_true;
        }
    }

    return caffc_false;
}
