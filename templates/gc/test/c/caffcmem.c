
#include "caffcmem.h"

#include <stdlib.h>
#include <string.h>

/* FIXME: remove and replace with exceptions */
#include <stdio.h>

void caffc_init() {
  /* NOOP */
}

void caffc_done() {
  /* NOOP */
}

/*
 * This allocator is only for testing, since it leaks memory.
 */
caffc_object_header* caffc_new(caffc_class_header* object_type, caffc_u32 object_size) {
    if (!object_type) {
        return null;
    }

    if (object_size < sizeof(caffc_object_header)) {
        printf("invalid malloc, prepare to die. requested object size: %ld, however object header size alone is: %ld\n", object_size, sizeof(caffc_object_header));
    }

    caffc_object_header* result = malloc(object_size);

    memset(result, 0, object_size);
    result->_caffc_class_header = object_type;

    return result;
}
