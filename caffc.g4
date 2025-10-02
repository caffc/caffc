grammar caffc;

compilationUnit:
    module
    useStatement*
    compileBlock*;

module: MODULE fqdn;

useStatement: use use_alias?;

compileBlock:
    nativeBlock
    | decoratorCall
    | tagDefinition
    | function
    | classDefinition
    | interfaceDefinition
    | variableDeclaration
    | block;

use: USE fqdn;
use_alias: AS ID;

nativeBlock: NATIVE;

function:
    tags? STATIC? ID genericsDeclarations? '(' extend (',' parameterDefinitions)? ')' ('->' returnType?)? block |
    tags? STATIC? ID genericsDeclarations? '(' parameterDefinitions? ')' ('->' returnType?)? block;

returnType:
  typeTuple |
  VOID;

classDefinition:
    tags? CLASS name genericsDeclarations? (IMPLEMENTS interfaceImplementations)? '{'
        fieldDeclaration*
        function*
    '}'
    ;

interfaceDefinition:
    tags? INTERFACE name genericsDeclarations? (EXTENDS interfaceImplementations )? '{' interfaceStatements '}'
    ;

name: ID;

interfaceImplementations:
    interfaceImplementation (',' interfaceImplementation)*;

// this represents a single "implements" or "extends" for an interface
// i.e. com.germaniumhq.Item<str>. Since it's a specific implementation it
// is an instantiation. Note that instantiations can still defer to
// other generics, i.e.:
//
// interface com.germaniumhq.Item<T> extends yolo.swag.Object<T> {
//   as_item() -> T
// }
//
// The previous interface just adds another method, that's also
// generic, however in the `extends` context it must refer to one
// of the generics defined in the `com.germaniumhq.Item` declaration.
interfaceImplementation: fqdn genericsInstantiations?;

interfaceStatements:
    functionDeclaration*
    ;

functionDeclaration:
    tags? ID genericsDeclarations? '(' extend (',' parameterDefinitions)? ')' ('->' returnType?)? |
    tags? ID genericsDeclarations? '(' parameterDefinitions? ')' ('->' returnType?)?;

tagDefinition:
    tags? TAG ID '{' fieldDeclaration* '}'
    ;

fieldDeclaration: typeName ID (',' ID)*;

statement:
  decoratorCall |
  function |
  variableDeclarations |
  return |
  controlFlow |
  nativeBlock |
  whileBlock |
  forBlock |
  ifBlock |
  tryCatchBlock |
  expression;

block: '{' statement* '}';

whileBlock: WHILE expression block;
forBlock: FOR (initExpression=expression|variableDeclarations) ';' conditionExpression=expression ';' incrementExpression=expression block;
ifBlock: IF expression (block|return|controlFlow) |
  IF expression block ELSE block;
tryCatchBlock: TRY block (CATCH '(' classType ID ')')? (FINALLY block)?;

return:
  RETURN expression |
  RETURN |
  YIELD expression;

decoratorCall:
  '@' expression function |
  '@' expression decoratorCall;

controlFlow:
  BREAK |
  CONTINUE;

variableDeclarations:
  varType variableDeclaration (',' variableDeclaration)*;

variableDeclaration: ID ('=' expression)?;

varType:
    VAR | typeName;

tags: tag+;

tag:
 SHARP fqdn |
 SHARP fqdn '(' tagParam (',' tagParam)* ')';

tagParam:
  ID '=' constExpression |
  constExpression;

constExpression:
    NUMBER | STRING;

fqdn:
  ID             # FqdnId
  | fqdn '.' ID  # FqdnMultiple
  ;

// keep in sync with CaffcPebblesExtension and Expression
expression:
  NUMBER                                                                                           # ExNumber
  | STRING                                                                                         # ExString
  | ID                                                                                             # ExId
  | expression '.' ID                                                                              # ExDotAccess
//  | expression '?.' ID                                                                           # ExNullableDotAccess
  | NEW newType '(' expressionTuple? ')'                                                           # ExNewObject
  | NEW newType ('[' expression ']')+                                                              # ExNewArray
  | expression genericsInstantiations? '(' expressionTuple? ')'                                    # ExFnCall
  | arraryExpression=expression '[' indexExpression=expression ']'                                 # ExIndexAccess
  | '(' typeName ')' expression                                                                    # ExCast
  | '(' expression ')'                                                                             # ExParens
  | ('!'|NOT) expression                                                                           # ExBoolNot
  | '~' expression                                                                                 # ExBitNot
  | '-' expression                                                                                 # ExUnaryMinus
//  | expression NOT? IN expression                                                                # ExNotIn
  | leftExpression=expression ('*'|'%') rightExpression=expression                                 # ExMulMod
  | leftExpression=expression '/' rightExpression=expression                                       # ExDiv
  | leftExpression=expression ('+'|'-') rightExpression=expression                                 # ExAddSub
  | leftExpression=expression ('<<'|'>>') rightExpression=expression                               # ExShift
  | leftExpression=expression ('<'|'<='|'>='|'>') rightExpression=expression                       # ExLtLteGtGte
  | leftExpression=expression ('=='|'!=') rightExpression=expression                               # ExEqNeq
  | leftExpression=expression '&' rightExpression=expression                                       # ExBitAnd
  | leftExpression=expression '^' rightExpression=expression                                       # ExBitXor
  | leftExpression=expression '|' rightExpression=expression                                       # ExBitOr
  | leftExpression=expression AND rightExpression=expression                                       # ExBoolAnd
  | leftExpression=expression OR rightExpression=expression                                        # ExBoolOr
  | checkExpression=expression
    ('?' trueExpression=expression ':'|'?:')
    falseExpression=expression                                                                     # ExTernary
  | leftExpression=expression
    ('<<='|'>>='|'&='|'|='|'^='|'*='|'/='|'+='|'-=')
    rightExpression=expression                                                                     # ExOpAssign
  | leftExpression=expression '=' rightExpression=expression                                       # ExAssign
  ;

expressionTuple:
  expression (',' expression)*;

extend:
    EXTENDS classType;

parameterDefinitions:
    parameterDefinition (',' parameterDefinition)*;

parameterDefinition:
    tags? typeName ID STAR? ('=' expression)?;

typeName
    : classType           # TypeClass
    | primitiveTypeName   # TypePrimitive
    | functionType        # TypeFunction
    | typeName ('[' ']')+ # TypeArray
    ;

newType:
    classType             # NewTypeClass
    | primitiveTypeName   # NewTypePrimitive
    | newType ('[' ']')+  # NewTypeArray
    ;

primitiveTypeName:
    'u8' | 'i8' | 'u16' | 'i16' | 'u32' | 'i32' | 'u64' | 'i64' | 'f32' | 'f64' | 'ptr' | 'bool';

classType:
    fqdn genericsInstantiations?;

// genericsDeclarations defines generic types in the function implementation
// that will be further checked by the language later, with the actual in-
// stantiated types. While the declaration can be type restricted, in the
// implementation of the class/function the type restriction will be used
// with its `IS` type, or `obj` if no type was restricted.
genericsDeclarations:
  '<' genericDeclaration (',' genericDeclaration)* '>'
  ;

genericDeclaration:
  ID                          # GenericDeclarationId
  | ID IS typeName            # GenericDeclarationRestricted
  ;

// genericsInstantiation defines the concrete types that will be used by the
// compiler when instantiating the generics - new list<Item> or call<Item>(...)
genericsInstantiations:
  '<' '>'                             # GenericsInstantiationEmpty
  | '<' typeName (',' typeName)* '>'  # GenericsInstantiationType
  ;

functionType:
    FN genericsInstantiations?;

typeTuple:
    typeName (',' typeName)*
    ;

// ***********************************************************************
// * lexer
// ***********************************************************************

STRING: SHORT_STRING | LONG_STRING;

/// shortstring     ::=  "'" shortstringitem* "'" | '"' shortstringitem* '"'
/// shortstringitem ::=  shortstringchar | stringescapeseq
/// shortstringchar ::=  <any source character except "\" or newline or the quote>
fragment SHORT_STRING:
    '\'' (STRING_ESCAPE_SEQ | ~[\\\r\n\f'])* '\''
    | '"' ( STRING_ESCAPE_SEQ | ~[\\\r\n\f"])* '"'
;
/// longstring      ::=  "'''" longstringitem* "'''" | '"""' longstringitem* '"""'
fragment LONG_STRING: '\'\'\'' LONG_STRING_ITEM*? '\'\'\'' | '"""' LONG_STRING_ITEM*? '"""';

/// longstringitem  ::=  longstringchar | stringescapeseq
fragment LONG_STRING_ITEM: LONG_STRING_CHAR | STRING_ESCAPE_SEQ;

/// longstringchar  ::=  <any source character except "\">
fragment LONG_STRING_CHAR: ~'\\';

/// stringescapeseq ::=  "\" <any source character>
fragment STRING_ESCAPE_SEQ: '\\' . | '\\' NEWLINE;

fragment NEWLINE: '\r\n' | '\r' | '\n';

// comments, for single line or for blocks
COMMENT: '/' '/' .*? '\n' -> skip;
BLOCK_COMMENT : '/*' ( BLOCK_COMMENT | . )*? '*/'  -> skip ;

WS: [ \n\t\r] -> skip;

AS: 'as';
AND: 'and';
BREAK: 'break';
CATCH: 'catch';
CLASS: 'class';
CONTINUE: 'continue';
ELSE: 'else';
EXTENDS: 'extends';
FINALLY: 'finally';
FOR: 'for';
IF: 'if';
IMPLEMENTS: 'implements';
IN: 'in';
INTERFACE: 'interface';
IS: 'is';
MODULE: 'module';
NEW: 'new';
NOT: 'not';
OR: 'or';
RETURN: 'return';
STATIC: 'static';
TAG: 'tag';
THROW: 'throw';
TRY: 'try';
USE: 'use';
VAR: 'var';
VOID: 'void';
WHILE: 'while';
YIELD: 'yield';

U8: 'u8';
I8: 'i8';
U16: 'u16';
I16: 'i16';
U32: 'u32';
I32: 'i32';
U64: 'u64';
I64: 'i64';
F32: 'f32';
F64: 'f64';

// KEYWORDS
NATIVE: 'native' WS+ NATIVE_FRAGMENT;
fragment NATIVE_FRAGMENT: '{' ((~'}') | NATIVE_FRAGMENT)* '}';

// other
FN: 'fn';
SHARP: '#';
STAR: '*';
DOT: '.';
ID: LETTER (LETTER | DIGIT)*;

NUMBER
    : IntegerConstant
    | FloatingConstant;

fragment IntegerConstant
    : '-'? DecimalConstant IntegerSuffix?
    | '-'? OctalConstant IntegerSuffix?
    | '-'? HexadecimalConstant IntegerSuffix?
    | '-'? BinaryConstant
    ;

fragment BinaryConstant
    : '0' [bB] [0-1]+
    ;

fragment DecimalConstant
    : NonzeroDigit Digit*
    ;

fragment OctalConstant
    : '0' OctalDigit*
    ;

fragment HexadecimalConstant
    : HexadecimalPrefix HexadecimalDigit+
    ;

fragment HexadecimalPrefix
    : '0' [xX]
    ;

fragment Digit
    : [0-9]
    ;

fragment NonzeroDigit
    : [1-9]
    ;

fragment OctalDigit
    : [0-7]
    ;

fragment HexadecimalDigit
    : [0-9a-fA-F]
    ;

fragment FloatingConstant
    : DecimalFloatingConstant
    ;

fragment DecimalFloatingConstant
    : FractionalConstant ExponentPart? FloatingSuffix?
    | DigitSequence ExponentPart FloatingSuffix?
    ;

fragment FractionalConstant
    : DigitSequence? '.' DigitSequence
    | DigitSequence '.'
    ;

fragment ExponentPart
    : [eE] Sign? DigitSequence
    ;

fragment Sign
    : [+-]
    ;

fragment IntegerSuffix
    : '_' U8
    | '_' I8
    | '_' U16
    | '_' I16
    | '_' U32
    | '_' I32
    | '_' U64
    | '_' I64
    ;


fragment FloatingSuffix
    : '_' F32
    | '_' F64
    ;

DigitSequence
    : Digit+
    ;

fragment HexadecimalFractionalConstant
    : HexadecimalDigitSequence? '.' HexadecimalDigitSequence
    | HexadecimalDigitSequence '.'
    ;

fragment BinaryExponentPart
    : [pP] Sign? DigitSequence
    ;

fragment HexadecimalDigitSequence
    : HexadecimalDigit+
    ;


fragment LETTER: [_a-zA-Z];
fragment DIGIT: [0-9];
