package com.germaniumhq.caffc.compiler.model;

public enum Instruction {
    /*
     * Load a variable from the local variables onto the stack. A
     * new entry is created on the stack.
     *
     * o index
     * + variable value
     */
    LOAD_LOCAL_U8(0),  // lu8 index
    LOAD_LOCAL_I8(1),  // li8 index
    LOAD_LOCAL_I16(2), // li16 index
    LOAD_LOCAL_I32(3), // li32 index
    LOAD_LOCAL_I64(4), // li64 index
    LOAD_LOCAL_F32(5), // lf32 index
    LOAD_LOCAL_F64(6), // lf64 index
    LOAD_LOCAL_PTR(7), // lptr index

    /*
     * Store a variable from the stack into local variables. The
     * variable is popped from the stack.
     *
     * o index
     * - variable value
     */
    STORE_LOCAL_U8(8), // su8 index
    STORE_LOCAL_I8(9),
    STORE_LOCAL_I16(10),
    STORE_LOCAL_I32(11),
    STORE_LOCAL_I64(12),
    STORE_LOCAL_F32(13),
    STORE_LOCAL_F64(14),
    STORE_LOCAL_PTR(15),

    /*
     * Loads a global variable into the stack
     * o index (?)
     * + variable value
     */
    LOAD_GLOBAL_U8(16),
    LOAD_GLOBAL_I8(17),
    LOAD_GLOBAL_I16(18),
    LOAD_GLOBAL_I32(19),
    LOAD_GLOBAL_I64(20),
    LOAD_GLOBAL_F32(21),
    LOAD_GLOBAL_F64(22),
    LOAD_GLOBAL_PTR(23),

    /*
     * Store a global variable.
     * o index
     * - variable value
     */
    STORE_GLOBAL_U8(24),
    STORE_GLOBAL_I8(25),
    STORE_GLOBAL_I16(26),
    STORE_GLOBAL_I32(27),
    STORE_GLOBAL_I64(28),
    STORE_GLOBAL_F32(29),
    STORE_GLOBAL_F64(30),
    STORE_GLOBAL_PTR(31),

    /*
     * Loads a constant onto the stack. The bytes are low endian
     * and represent the actual value to store. A new entry is
     * created on the stack.
     *
     * o constant
     * + constant value
     */
    LOAD_CONST_U8(16), // load constants, a new entry is created on the stack
    LOAD_CONST_I8(17),
    LOAD_CONST_I16(18),
    LOAD_CONST_I32(19),
    LOAD_CONST_I64(20),
    LOAD_CONST_F32(21),
    LOAD_CONST_F64(22),
    LOAD_CONST_PTR(23),

    /*
     * Create an array object. The size of the array is popped from the stack.
     * A new ptr object is created on the stack.
     *
     * - array size
     * + ptr to array
     */
    NEW_ARRAY_U8(24),
    NEW_ARRAY_I8(25),
    NEW_ARRAY_I16(26),
    NEW_ARRAY_I32(27),
    NEW_ARRAY_I64(28),
    NEW_ARRAY_F32(29),
    NEW_ARRAY_F64(30),
    NEW_ARRAY_PTR(31),

    /*
     * Store an item into an array.
     * - ptr to array
     * - index
     * - value
     */
    STORE_ARRAY_U8(32),
    STORE_ARRAY_I8(33),
    STORE_ARRAY_I16(34),
    STORE_ARRAY_I32(35),
    STORE_ARRAY_I64(36),
    STORE_ARRAY_F32(37),
    STORE_ARRAY_F64(38),
    STORE_ARRAY_PTR(39),

    /*
     * Load an item from the array
     * - ptr to array
     * - index
     * + value
     */
    LOAD_ARRAY_U8(41),
    LOAD_ARRAY_I8(42),
    LOAD_ARRAY_I16(43),
    LOAD_ARRAY_I32(44),
    LOAD_ARRAY_I64(45),
    LOAD_ARRAY_F32(46),
    LOAD_ARRAY_F64(47),
    LOAD_ARRAY_PTR(48),

    /*
     * Stores a field into an object:
     * - ptr to object
     * - i16 field number?
     * - field value
     */
    STORE_FIELD_U8(49),
    STORE_FIELD_I8(50),
    STORE_FIELD_I16(51),
    STORE_FIELD_I32(52),
    STORE_FIELD_I64(53),
    STORE_FIELD_F32(54),
    STORE_FIELD_F64(55),
    STORE_FIELD_PTR(56),

    /*
     * Loads a field from an object
     * - ptr to object
     * - i16 field number?
     * + field value
     */
    LOAD_FIELD_U8(57),
    LOAD_FIELD_I8(58),
    LOAD_FIELD_I16(59),
    LOAD_FIELD_I32(60),
    LOAD_FIELD_I64(61),
    LOAD_FIELD_F32(62),
    LOAD_FIELD_F64(63),
    LOAD_FIELD_PTR(64),

    /*
     * Create a new object instance
     * - ptr to class
     * + ptr to object
     */
    NEW_OBJECT(49),

    /*
     * Call a function
     * FIXME: I'm not sure on the bytecode format to specify the function
     * o argument count
     * - function arguments
     * - ptr to function definition
     * + return address
     */
    CALL(50),

    /*
     * Return from a function. The return value is popped, then
     * the return address, the return value restored, then the jump
     * to the code will happen.
     * - return value
     * - return address
     * + return value
     */
    RET(51),
    RET_U8(52),
    RET_I8(53),
    RET_I16(54),
    RET_I32(55),
    RET_I64(56),
    RET_F32(57),
    RET_F64(58),
    RET_PTR(59),

    /*
     * Casts a primitive to another primitive
     * o format
     * - variable value (old format)
     * + variable value (new format)
     */
    CAST(60),

    /*
     * jump to the given address
     */
    JMP(61),
    JZ(62),
    JNZ(63),

    ;

    private byte code;

    Instruction(int code) {
        this.code = (byte) code;
    }
}
