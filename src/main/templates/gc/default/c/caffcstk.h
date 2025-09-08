#ifndef caffcstk_h
#define caffcstk_h

/* include the primitive types */
#include "caffcpt.h"

typedef struct caffc_stack_frame_struct {
  ptr data_frame;
  u32 var_count;
} caffc_stack_frame;

typedef struct caffc_call_stack_struct {
  u32 call_count;
  caffc_stack_frame frames[0];
} caffc_call_stack;

/**
 * Register a stack frame call. The data_frame is a pointer to a contiguous
 * pointer array to caffc objects/arrays. The actual count of variables is
 * being passed in the var_count. The data_frame can be null if there's no
 * variables that are handled by the gc.
 *
 * data_frame is a list of pointers, each pointer that points to its local
 * variable location (as in also a pointer).
 *
 * i.e.:
 *
 * ```c
 * obj p; // translates to ptr
 *
 * ptr _caffc_locals[] = {
 *   &p;
 * }
 *
 * _caffc_stack_frame_register("main", _caffc_locals, 1);
 */
void _caffc_stack_frame_register(u8* function_name, ptr data_frame, u32 var_count);

/**
 * Unregister a stack frame call. The data_frame is the same pointer that was
 * used in the register method. It can be null if there's no variables that
 * are handled by the gc.
 */
void _caffc_stack_frame_unregister(ptr data_frame);

extern caffc_call_stack* _caffc_call_stack;

#endif  /* ifndef caffcstk_h */
