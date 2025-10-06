
#include "caffcstk.h"

#include <stddef.h>
#include <stdlib.h>
#include <string.h>

caffc_stack_frame* _caffc_current_stack_frame = caffc_null;
caffc_call_stack* _caffc_call_stack = caffc_null;

/**
 * Register a stack frame call. The data_frame is a pointer to a contiguous
 * pointer array to caffc objects/arrays. The actual count of variables is
 * being passed in the var_count. The data_frame can be null if there's no
 * variables that are handled by the gc.
 */
void _caffc_stack_frame_register(caffc_u8* function_name, caffc_ptr data_frame, caffc_u32 var_count) {
    caffc_stack_frame* new_frame = &(_caffc_call_stack->frames[_caffc_call_stack->call_count]);
    new_frame->data_frame = data_frame;
    new_frame->var_count = var_count;

    _caffc_call_stack->call_count++;
}

/**
 * Unregister a stack frame call. The data_frame is the same pointer that was
 * used in the register method. It can be null if there's no variables that
 * are handled by the gc.
 */
void _caffc_stack_frame_unregister(caffc_ptr data_frame) {
    _caffc_call_stack->call_count--;
}

