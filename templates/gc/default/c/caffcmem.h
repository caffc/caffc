#ifndef __caffcomem_h
#define __caffcomem_h

#include "caffco.h"
#include "caffcstk.h"

#include <stdlib.h>

/**
 * Function to be called to manually bootstrap the GC structures.
 * #public_api
 */
void caffc_init();

/**
 * Destroy the GC structures. To be called atexit() to ensure no leaks
 * remain.
 * #public_api
 */
void caffc_done();

/**
 * Allocate a new CaffC object. The object_size allows allocating more
 * memory than the actual object layout to accommodate for arrays, or
 * strings.
 * #public_api
 */
caffc_object_header* caffc_new(caffc_class_header* object_type, caffc_u32 object_size);

#endif
