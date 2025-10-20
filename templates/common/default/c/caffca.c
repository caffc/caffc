#include "caffca.h"

/* defined in the module_caffc.h */
extern caffc_class_header caffc_obj_arr_type;

caffc_bool caffc_is_array(caffc_ptr object) {
    caffc_object_header* caffc_obj;

    if (!object) {
        return caffc_false;
    }

    caffc_obj = (caffc_object_header*) object;

    return caffc_obj->_caffc_class_header == &caffc_obj_arr_type;
}

caffc_void caffc_bzero(caffc_ptr p, caffc_u32 size) {
    caffc_u64 i;
    caffc_u8* cp = (caffc_u8*) p;

    for (i = 0; i < size; i++) {
        *cp = 0;
        cp++;
    }
}
