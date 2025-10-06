#ifndef __caffcgcps_h
#define __caffcgcps_h

#include "caffcpt.h"

/**
 * caffc GarbageCollect Pointer Set.
 *
 * This is not a general purpose structure, but only used for the GC,
 * and to hold work pointer sets. Currently it uses a tree implementation.
 * Might move in the future to an optional hash set.
 */
typedef struct {
    caffc_ptr* data;
    caffc_i32 _capacity;
    caffc_i32 len;
} caffc_gc_pointer_set;

/**
 * Constructor.
 */
void caffc_gc_pointer_set_constructor(caffc_gc_pointer_set* _this, caffc_i32 default_capacity);

/**
 * Add an item into the set. If the item already exists, does nothing.
 */
void caffc_gc_pointer_set_add(caffc_gc_pointer_set* _this, caffc_ptr value);

/**
 * Pops an item from the set.
 */
caffc_ptr caffc_gc_pointer_set_pop(caffc_gc_pointer_set* _this);

/**
 * Destructor that deallocates the used resources.
 */
void caffc_gc_pointer_set_destructor(caffc_gc_pointer_set* _this);

#endif  /* ifndef __caffcgcps_h */
