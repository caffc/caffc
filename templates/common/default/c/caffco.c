#include "caffco.h"
#include "caffc_program_constants.h"

/**
 * Checks if a given object is an instance of a type.
 */
caffc_bool caffc_instanceof_check(caffc_ptr o, caffc_u32 ancestor_type_id) {
    caffc_object_header* header = caffc_null;
    caffc_u32 i = 0;
    caffc_inheritance_data* inheritance_data = caffc_null;

    /* null pointers can't be the instanceof anything */
    if (!o) {
        return caffc_false;
    }

    header = (caffc_object_header*) o;

    /* check ancestor_type_id bounds? */
    inheritance_data = _caffc_class_headers[header->_caffc_type_id].inheritance_data;

    for (i = 0; i < inheritance_data->count; i++) {
        if (inheritance_data->implemented_type_ids[i] == ancestor_type_id) {
            return caffc_true;
        }
    }

    /* everything implements the `obj` interface. we search it last because it's unlikely
       anyone actually calls it. */
    if (header->_caffc_type_id == CAFFC_OBJ_TYPE_ID) {
        return caffc_true;
    }

    return caffc_false;
}
