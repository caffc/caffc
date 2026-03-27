#include "caffco.h"

caffc_bool caffc_instanceof_check(caffc_ptr o, caffc_u32 type_id) {
    caffc_object_header* header;

    /* null pointers can't be the instanceof anything */
    if (!o) {
        return 0;
    }

    header = (caffc_object_header*) o;

    /* check type_id bounds? */
}