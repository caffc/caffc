#include "caffcmem.h"

#include "caffcms.h"
#include "caffcgcpl.h"

#include <stdlib.h>
#include <string.h>

/* FIXME: remove and replace with exceptions */
#include <stdio.h>

#include "caffcstk.h"

extern caffc_gc_pointer_list caffc_roots;
extern caffc_gc_pointer_list caffc_all_objects;
extern caffc_call_stack* _caffc_call_stack;

// every few MBs of allocated objects we run the GC
u32 total_allocated = 0;
#define GC_ALLOCATED_SIZE_AUTO_COLLECT_CALL (2 * 1024 * 1024)

/* forward declarations */
void caffc_gc_perform();

caffc_object_header* caffc_new(caffc_class_header* object_type, u32 object_size) {
    if (!object_type) {
        return null;
    }

    if (object_size < sizeof(caffc_object_header)) {
        printf("invalid malloc, prepare to die. requested object size: %ud, "
               "however object header size alone is: %ld\n", object_size, sizeof(caffc_object_header));
    }

    total_allocated += object_size;
    if (total_allocated > GC_ALLOCATED_SIZE_AUTO_COLLECT_CALL) {
        total_allocated = 0;
        caffc_gc_perform();
    }

    caffc_object_header* result = malloc(object_size);

    memset(result, 0, object_size);
    result->_caffc_class_header = object_type;

    /* add the current pointer */
    caffc_gc_pointer_list_add(&caffc_all_objects, result);

    return result;
}

void caffc_init() {
    caffc_gc_pointer_list_constructor(&caffc_all_objects, 16);

    u32 stack_size = sizeof(caffc_call_stack) + sizeof(caffc_stack_frame) * 1000;
    _caffc_call_stack = malloc(stack_size);
    memset(_caffc_call_stack, 0, stack_size);
}

void caffc_gc_perform() {
    caffc_gc_ms_mark();
    caffc_gc_ms_sweep();
}

void caffc_done() {
    caffc_gc_perform();

    caffc_gc_pointer_list_destructor(&caffc_all_objects);
    free(_caffc_call_stack);
}
