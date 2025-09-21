#include <stdlib.h>

#include "caffcgcpl.h" /* ptr list */
#include "caffcc.h" /* class */
#include "caffco.h" /* obj */
#include "caffca.h" /* array */

#include "caffcms.h"
#include "caffcstk.h"

#define caffc_gc_ms_is_marked(o) (((caffc_object_header*)o)->_caffc_flags & CAFFC_OBJECT_FLAGS_GC_MARKED)
#define caffc_gc_ms_set_marked(o) ((caffc_object_header*)o)->_caffc_flags |= CAFFC_OBJECT_FLAGS_GC_MARKED
#define caffc_gc_ms_clear_marked(o) ((caffc_object_header*)o)->_caffc_flags &= ~CAFFC_OBJECT_FLAGS_GC_MARKED

caffc_gc_pointer_list caffc_all_objects = {};

/**
 * Goes over all the root elements and recursively marks all the used
 * nodes.
 */
void caffc_gc_ms_mark() {
    caffc_gc_pointer_list work_list;
    caffc_object_header* object_header = null;
    caffc_class_header* class_header = null;
    u32 i = 0, j = 0;
    ptr field_ptr = null;
    u32 field_count = 0;

    caffc_gc_pointer_list_constructor(&work_list, 16);

    for (i = 0; i < _caffc_call_stack->call_count; i++) {
        for (j = 0; j < _caffc_call_stack->frames[i].var_count; j++) {
            /* the data_frame points to a pointer list. each pointer in that list points to an actual
             * object reference; that reference is also a pointer. we need to get the object reference
             * pointer.*/
            void*** data_frame_ptr = _caffc_call_stack->frames[i].data_frame;
            caffc_gc_pointer_list_add(&work_list, **(data_frame_ptr + j));
        }
    }

    while (work_list.len) {
        ptr object = caffc_gc_pointer_list_remove(&work_list, work_list.len - 1);
        object_header = (caffc_object_header*) object;

        /* duplicate pointer, we already processed it */
        if (caffc_gc_ms_is_marked(object)) {
            continue;
        }

        caffc_gc_ms_set_marked(object);

        /* arrays have dynamic references */
        if (caffc_is_array(object)) {
            caffc_array_header* array_header = (caffc_array_header*)object_header;
            field_ptr = array_header->_caffc_data;
            field_count = array_header->_caffc_field_count;
        } else { /* regular object */
            class_header = object_header->_caffc_class_header;
            field_ptr = (ptr)object_header + sizeof(caffc_object_header);
            field_count = class_header->field_count;
        }

        for (i = 0; i < field_count; i++) {
            ptr actual_field_ptr = *((void**) field_ptr);
            if (!actual_field_ptr || caffc_gc_ms_is_marked(actual_field_ptr)) {
                continue;
            }

            caffc_gc_pointer_list_add(&work_list, actual_field_ptr);

            field_ptr += sizeof(ptr);
        }
    }

    caffc_gc_pointer_list_destructor(&work_list);
}

/**
 * Goes over all the allocated objects and frees them if they're
 * not marked.
 */
void caffc_gc_ms_sweep() {
    i32 i;
    ptr object;

    for (i = caffc_all_objects.len - 1; i >= 0; i--) {
        object = caffc_gc_pointer_list_get(&caffc_all_objects, i);

        if (caffc_gc_ms_is_marked(object)) {
            caffc_gc_ms_clear_marked(object);
        } else {
            free(object);
            caffc_gc_pointer_list_remove(&caffc_all_objects, i);
        }
    }
}

