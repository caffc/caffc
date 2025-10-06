#ifndef __caffcpt_h
#define __caffcpt_h

/*
 * Primitive types for CaffC. These types are used also in code such as
 * GC, or internal CaffC structures, even if they're not managed GC.
 */
#ifndef caffc_bool
  #define caffc_bool unsigned char
#endif

#ifndef caffc_true
  #define caffc_true 1
#endif

#ifndef caffc_false
  #define caffc_false 0
#endif

#ifndef caffc_u8
  #define caffc_u8 unsigned char
#endif /* not caffc_u8 */

#ifndef caffc_i8
  #define caffc_i8 signed char
#endif /* not caffc_i8 */

#ifndef caffc_u16
  #define caffc_u16 unsigned short
#endif /* not caffc_u16 */

#ifndef caffc_i16
  #define caffc_i16 short
#endif /* not caffc_i16 */

#ifndef caffc_u32
  #define caffc_u32 unsigned int
#endif /* not caffc_u32 */

#ifndef caffc_i32
  #define caffc_i32 int
#endif /* not caffc_i32 */

#ifndef caffc_u64
  #define caffc_u64 unsigned long long
#endif /* not caffc_u64 */

#ifndef caffc_i64
  #define caffc_i64 long long
#endif /* not caffc_i64 */

/* FIXME: not sure this can be guaranteed */
#ifndef caffc_f32
  #define caffc_f32 float
#endif /* not caffc_f32 */

#ifndef caffc_f64
  #define caffc_f64 double
#endif /* not caffc_f64 */

#ifndef caffc_ptr
  #define caffc_ptr void*
#endif /* not caffc_ptr */

#ifndef caffc_null
  #define caffc_null (void*)0
#endif /* not caffc_null */

#endif  /* ifndef __caffcpt_h */
