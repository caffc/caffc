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
    tags? ID genericsDeclarations? '(' extend (',' parameterDefinition)? ')' ('->' returnType?)? block |
    tags? ID genericsDeclarations? '(' parameterDefinition? ')' ('->' returnType?)? block;

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
    tags? ID genericsDeclarations? '(' extend (',' parameterDefinition)? ')' ('->' returnType?)? |
    tags? ID genericsDeclarations? '(' parameterDefinition? ')' ('->' returnType?)?;

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
  | '(' expression ')'                                                                             # ExParens
  | expression '.' ID                                                                              # ExDotAccess
//  | expression '?.' ID                                                                           # ExNullableDotAccess
  | NEW newType '(' expressionTuple? ')'                                                           # ExNewObject
  | NEW newType ('[' expression ']')+                                                              # ExNewArray
  | expression genericsInstantiations? '(' expressionTuple? ')'                                    # ExFnCall
  | arraryExpression=expression '[' indexExpression=expression ']'                                 # ExIndexAccess
  | ('!'|NOT) expression                                                                           # ExBoolNot
  | '~' expression                                                                                 # ExBitNot
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
  expressionTuple (',' expressionTuple)+ |
  expression;

extend:
    EXTENDS classType;

parameterDefinition:
    tags? typeName ID STAR? ('=' expression)? |
    parameterDefinition (',' parameterDefinition)+;

typeName:
    classType             # TypeClass
    | primitiveTypeName   # TypePrimitive
    | functionType        # TypeFunction
    | typeName ('[' ']')+ # TypeArray
    ;

newType:
    classType             # NewTypeClass
    | primitiveTypeName   # NewTypePrimitive
    | newType ('[' ']')+  # NewTypeArray
    ;

// FIXME: add unsigned types as well? this is C after all, a bunch of messages
//        are unsigned unfortunately.
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
TAG: 'tag';
THROW: 'throw';
TRY: 'try';
USE: 'use';
VAR: 'var';
VOID: 'void';
WHILE: 'while';
YIELD: 'yield';

// KEYWORDS
NATIVE: 'native' WS+ NATIVE_FRAGMENT;
fragment NATIVE_FRAGMENT: '{' ((~'}') | NATIVE_FRAGMENT)* '}';

// other
FN: 'fn';
SHARP: '#';
STAR: '*';
DOT: '.';
ID: LETTER (LETTER | DIGIT)*;

NUMBER: [0-9]+;

fragment LETTER: [_a-zA-Z];
fragment DIGIT: [0-9];
