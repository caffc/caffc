#ifndef caffcstk_h
#define caffcstk_h

/* include the primitive types */
#include "caffcpt.h"

/**
 * Forward declaration for the `caffc.str` since that one will come
 * from the `module_caffc.h` later, that depends on this file.
 */
struct caffc_str;

typedef struct caffc_stack_frame_struct {
  struct caffc_str* function_name;
  caffc_ptr data_frame;
  caffc_u32 var_count;
} caffc_stack_frame;

typedef struct caffc_call_stack_struct {
  caffc_u32 call_count;
  caffc_stack_frame frames[];
} caffc_call_stack;

/**
 * Register a stack frame call. The data_frame is a pointer to a contiguous
 * pointer array to caffc objects/arrays. The actual count of variables is
 * being passed in the var_count. The data_frame can be null if there's no
 * variables that are handled by the gc.
 *
 * data_frame is a pointer to a C array of pointers, each pointer pointing
 * to the variable location that is tracking. The variable in turn is also
 * a pointer that may be null.
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
void _caffc_stack_frame_register(
  struct caffc_str* function_name,
  caffc_ptr data_frame,
  caffc_u32 var_count);

/**
 * Unregister a stack frame call. The data_frame is the same pointer that was
 * used in the register method. It can be null if there's no variables that
 * are handled by the gc.
 */
void _caffc_stack_frame_unregister(
  caffc_ptr data_frame);

extern caffc_call_stack* _caffc_call_stack;

#endif  /* ifndef caffcstk_h */
