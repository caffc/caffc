#ifndef __caffcpt_h
#define __caffcpt_h

/*
 * Primitive types for CaffC. These types are used also in code such as
 * GC, or internal CaffC structures, even if they're not managed GC.
 */
#include <stdbool.h>

#ifndef u8
    #define u8 unsigned char
#endif /* not u8 */

#ifndef i8
    #define i8 signed char
#endif /* not i8 */

#ifndef u16
    #define u16 unsigned short
#endif /* not u16 */

#ifndef i16
    #define i16 short
#endif /* not i16 */

#ifndef u32
    #define u32 unsigned int
#endif /* not u32 */

#ifndef i32
    #define i32 int
#endif /* not i32 */

#ifndef u64
    #define u64 unsigned long long
#endif /* not u64 */

#ifndef i64
    #define i64 long long
#endif /* not i64 */

/* FIXME: not sure this can be guaranteed */
#ifndef f32
    #define f32 float
#endif /* not f32 */

#ifndef f64
    #define f64 double
#endif /* not f64 */

#ifndef ptr
#define ptr void*
#endif /* not ptr */

#ifndef null
#define null (void*)0
#endif /* not null */

#endif  /* ifndef __caffcpt_h */
