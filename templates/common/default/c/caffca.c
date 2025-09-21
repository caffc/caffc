#include "caffca.h"

/* defined in the module_caffc.h */
extern caffc_class_header caffc_obj_arr_type;

bool caffc_is_array(ptr object) {
    if (!object) {
        return false;
    }

    caffc_object_header* caffc_obj = (caffc_object_header*) object;

    return caffc_obj->_caffc_class_header == &caffc_obj_arr_type;
}
