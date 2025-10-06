#ifndef __caffcgcpl_h
#define __caffcgcpl_h

#include "caffcpt.h"

/**
 * caffc GarbageCollect Pointer List.
 *
 * This is not a general purpose structure, but only used for the GC,
 * and to hold work lists.
 */
typedef struct {
    caffc_ptr* data;
    caffc_i32 _capacity;
    caffc_i32 len;
} caffc_gc_pointer_list;

/**
 * Create a pointer list.
 */
void caffc_gc_pointer_list_constructor(caffc_gc_pointer_list* _this, caffc_i32 default_capacity);

/**
 * Destroy the pointer list.
 */
void caffc_gc_pointer_list_destructor(caffc_gc_pointer_list* _this);

/**
 * Add an item into the list. If the capacity is not enough, the
 * list will be expanded dynamically.
 */
void caffc_gc_pointer_list_add(caffc_gc_pointer_list* _this, caffc_ptr value);

/**
 * Removes an item from the list. The last pointer from the list
 * will replace the removed index.
 *
 * _always remove right to left_, to minimize moves.
 */
caffc_ptr caffc_gc_pointer_list_remove(caffc_gc_pointer_list* _this, caffc_i32 index);

/**
 * Gets an item from the list.
 */
caffc_ptr caffc_gc_pointer_list_get(caffc_gc_pointer_list* _this, caffc_i32 index);

#endif  /* ifndef __caffcgcpl_h */
