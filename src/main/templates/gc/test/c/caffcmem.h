#ifndef __caffcomem_h
#define __caffcomem_h

#include "caffco.h"

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
 * Allocate a new object.
 */
caffc_object_header* caffc_new(caffc_class_header* object_type, u32 object_size);

#endif
