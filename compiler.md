## Overview
The compiler works in a few phases:
1. **Loading of the compilation units.** Currently all of them are loaded in memory. Antlr is used to read an antlr specific AST.  Antlr does both the lexing and parsing in one step. This _antlr specific AST_ is converted to our _model AST_.
2. **Resolving of the types.** The code has references to things that might not exist, are behind generics, are aliased, etc. For all of these, we need to find the actual things that are behind. `SymbolSearch` objects are used to resolve actual `Symbol` items.
3. **Transforming the AST into instructions** in a linear form. This *linear form* is what gets used for rendering as models in the template.

## 1. Loading Of The Compilation Units
Initially each compilation unit (`*.caffc` file) is read into ANTLR's AST, then immediately converted to an internal AST format that doesn't depend on the ANTLR's parser. These internal types follow here:
### TypeName
As the name implies, represents the typeName of something. The typeName has:
* module
* clazz (optional)
* name
* dataType

The dataType in turn says what kind of data the typeName represents. In case it represents an object that's not yet resolvable its dataType will be `UNRESOLVED` until is registered.
### Expression
An expression represents one or more instructions that do something with variables and operators. Expressions also have a typeName, and the typeName can be derived from the operator itself. For example, an `i8` + `i16` variable will yield an `i16` variable.
### AstItem
The compiler parses the ANTLR AST nodes into internal `AstItem` instances. The `AstItem` interface exists to allow navigating up the scopes of the functions, classes, etc. when having to resolve variables.

To quickly navigate to the first parent with the given class, we can call `findAstParent` within the class.
### Scope
A scope represents a container for names, that will be resolved to `Symbol`s from the AST. For example the local variable `i` will be resolved by a `for` scope, as a Local variable that will return `i16` as typeName and of course its name `i`.

All global variables, functions and their statements (not definitions!), etc. are stored as close as possible to their owner (in the class that defines them, or module, etc).
* `Program`
* `Module`
* `CompilationUnit`
* `Clazz`
* `ClassDefinition`
* `Function`
### Symbol
A symbol represents a resolved name in the context of a Scope. A Symbol has a `name`, a `typeSymbol` and a `typeName` (a fqdn: `module:[function_class]:name:data_type`).  Symbols can be resolved from a given `AstItem` by its name and generics definition, using a `SymbolSearch` object via a `SymbolResolver`.

* "Container" Symbols
	* `PrimitiveSymbol`
	* `Module`
	* `ClassDefinition`
	* `InterfaceDefinition`
	* `Struct`
	* `FunctionDefinition`
* Variable Symbols
	* `Parameter`
	* `Field`
	* `VariableDeclaration`
All the "container" symbols have the `typeSymbol` pointing to themselves, since the name of `i32` is actually the `i32` primitive itself. Variable Symbols will have the name as the name of the actual variable, and the `typeSymbol` pointing to the actual type of the variable. `i32 x` would have the name as `x` and the `typeSymbol` as a reference to a `PrimitiveSymbol`.

> [!QUESTION] Unclear
> There's an obvious logical mismatch having the `typeSymbol` for variable symbols and container symbols. It makes no sense having the `typeSymbol` of a container symbol pointing to itself. Probably the `typeSymbol` should remain only for the variable symbols somehow.

### Program Structure

```plantuml
class Program <<Scope, ModuleProvider, AstItem>> {
  + stringConstantMap: Map<String, StringConstant>
  + requestTypeResolve(owner: AstItem, typeName: str): Type
}

class Module <<Scope, Symbol>> {
  + name: str
  + stringConstants: Set<str>
  + moduleArrays: Map<Type, i32>
  + registerArray(symbolTypeName: Type, dimensions: i32)
}

class FunctionDefinition <<Symbol>> {
  + name: str
  + returnType: Type
  + module: str
  + tags: Tags
}

class PrimitiveSymbol <<Symbol>> {
  + primitiveTypeName: Type
}

class InterfaceDefinition <<Symbol>> {
  + name: str
  + typeName: Type
  + tags: Tags
}

class ClassDefinition <<Symbol>> {
  + name: str
  + typeName: Type
  + gcFieldsCount: int
  + tags: Tags
}

class Struct <<Symbol>> {
  + name: str
  + typeName: Type
  + tags: Tags
}

class GenericDefinitions {
}

class GenericDefinition {
  name: str
  typeRestriction: Type
}

class Field <<Symbol>> {
  + owner: AstItem
  + typeName: Type
  + name: str
}

class Parameter <<AstItem, Symbol>> {
  + owner: AstItem
  + typeName: Type
  + name: str
}

Program *-- "2..*" Module
Module *-- "0..*" InterfaceDefinition
Module *-- "0..*" ClassDefinition
Module *-- "0..*" FunctionDefinition
Module *-- "0..*" Struct
Module o-- "0..*" Module: <<usedModules>>
ClassDefinition *-- "0..*" FunctionDefinition : functions
ClassDefinition *-- "0..*" Field : fields
InterfaceDefinition *-- "0..*" FunctionDefinition : functions
InterfaceDefinition *-- GenericDefinitions
FunctionDefinition *-- Parameter
ClassDefinition *-- GenericDefinitions
FunctionDefinition *-- GenericDefinitions
GenericDefinitions *-- "0..*" GenericDefinition: definitions
Struct *-- GenericDefinitions
Struct *-- "0..*" Field : fields
```
The actual classes that contain the code being generated:
```plantuml
interface AstItem {
  + recurseResolveTypes(): void
}

interface CompileBlock extends AstItem {
}

interface Scope extends AstItem {
  + resolve(name: str): Symbol
}

class CompilationUnit <<Scope, AstItem>> {
  + module: Module
  + compilationUnitPath: str
  + usedModules: map<str, str>
}

class Clazz <<CompileBlock, AstItem, Scope>> implements CompileBlock {
  + definition: ClassDefinition
  + functions: List<Function>
  + fields: List<Field>
}

class Function <<CompileBlock, Scope>> implements CompileBlock {
  + definition: FunctionDefinition
  + statements: List<Statement>
  + variables: List<VariableDeclaration>
}

class NativeBlock implements CompileBlock, Statement {
}

class Module <<Scope>> {
}

class Program <<Scope>> {
}

interface Expression extends Statement {
} 

class ReturnInstruction implements Statement {
}

interface Statement {
  + asLinearForm(): AsmLinearFormResult
}

Function *-- "0..*" Statement: statements
Program *-- "2..*" Module
Module o-- "1..*" CompilationUnit
CompilationUnit *-- "0..*" CompileBlock
```

## 2. Symbol Resolving
After the _new_ compilation units are loaded into memory, the 2nd phase, that is the symbol resolving takes effect. For this, all the names and types are linked to actual things, with generics resolved.
For this to happen, on each `CompilationUnit`, the `AstItem.recurseResolveTypes()` function will be called. This will do the resolving internally.

```plantuml
interface Symbol {
  + name: str
}

interface Scope extends AstItem {
  + resolve(name: SymbolSearch): Symbol
}

interface AstItem {
  + recurseResolveTypes(): void
}

class TypeName {
  + module: str
  + functionClass: str
  + name: str
  + dataType: DataType
}


class SymbolResolver {
  + resolveFqdn(owner: AstItem, req: SymbolSearch): Symbol
}

class SymbolSearch {
  + name: str
}

class GenericDefinition {
  + name: str
  + typeRestriction: TypeName
  - typeRestrictionSearch: SymbolSearch
}

AstItem ..> SymbolResolver: << uses >>
SymbolResolver ..> SymbolSearch: << input >>
SymbolResolver ..> Symbol: << returns >>
Scope ..> SymbolSearch: << uses>>
SymbolSearch *-- "0..*" SymbolSearch: generics
Symbol *-- TypeName
TypeName *-- "0..*" TypeName: generics
SymbolSearch ..> TypeName: <<same tree\nstructure>>
```

```java
class A<U is Item> {
  add<T is list<U>>(T t, U u) -> T {
    T x = t
    x.add(u)
    return x
  }
}
```
When we have the following call:
```java
A<CustomItem> a = new A<CustomItem>()  // here T, is still generic for add<T>()
list items = new list()                // default generics, T is obj

var x = a.add<CustomItem>(items, new CustomItem()) // x is list<CustomItem> here
var y = a.add<CustomItem>(items, new OtherItem())  // y is list<CustomItem> here
var z = a.add<OtherItem>(items, new OtherItem())   // z is list<OtherItem> here
var t = a.add(items, new CustomItem())             // t is list<Item> here
```
When copying the function definitions, only the generics defined in the parent class are changed with their more restrictive types.
### AstItem.recurseResolveTypes()
This function is important enough, it needs its own section. It's purpose is two-fold:
1. It descends from the top of the `CompilationUnit` downwards ensuring all symbols (classes, variables, functions) are resolvable for a compilation unit.
2. It allows each `AstItem` to call the `SymbolResolver`, that in turn will search the `AstItem` upwards for `Scope` items. The `Scope` objects own the used classes, functions or variables for which we're doing the search. The search itself is captured into a `SymbolSearch` that has beside the name, potential _unresolved_ generics information. The `SymbolResolver` will resolve the generics information as well when returning the `Symbol`.
> [!Important]
> The `SymbolResolver` will resolve the generics information as well when returning the `Symbol`.
### Generics, SymbolSearch and Arrays
```plantuml
interface Scope {
}

class SymbolSearch {
  + name: str
  + generics: SymbolSearch[]
}

class TypeName {
  + module: str
  + clazz: str
  + name: str
  + dataType: DataType
}

class ClassDefinition {
  + generics: GenericsDefinitions
  + instantiateGenericsCopy(typeName: TypeName): ClassDefinition
}

class GenericDefinitions {
  + generics: GenericDefinition[]
}

class GenericDefinition {
  + name: str
  + typeRestriction: TypeName
  - typeRestrictionSearch: SymbolSearch
}

Scope *-- ClassDefinition
SymbolSearch *-- "0..*" SymbolSearch: generics
SymbolSearch ..> TypeName: <<resolves to>>
ClassDefinition ..> TypeName: <<uses for generics\ninstantiation>>
ClassDefinition *-- GenericDefinitions
GenericDefinitions *-- "0..*" GenericDefinition
GenericDefinition *-> "0..*" GenericDefinition: generics
```


## 3. Linear Instructions
The AST in turn will be serialized as `AsmInstruction` instances. Same with variables, they're now `AsmVar` instances if they're actual variables (so values can be assigned to them), or `AsmValue` in case they contain values, but might be constants as well. An `AsmVar` is an `AsmValue`.

The key is that each function (either standalone or from a class) will have its instructions converted into `AsmInstruction`s. For this to work each `Statement` (this also means `Expression` instances) knows how to transform itself into linear form.

```plantuml
class Function <<CompileBlock, Scope>> implements CompileBlock {
  + definition: FunctionDefinition
  + statements: List<Statement>
  + instructions: List<AsmInstruction>
  + variables: List<VariableDeclaration>
}

interface Expression extends Statement {
} 

class ReturnInstruction implements Statement {
}

interface Statement {
  + asLinearForm(block: Block): AsmLinearFormResult
}

class AsmLinearFormResult {
  + value: AsmValue
  + instructions: AsmInstruction[]
}

interface AsmValue {}

interface AsmVar extends AsmValue {}

interface AsmInstruction {}

class AsmAssign <<asm>> implements AsmInstruction {}
class IfJmp <<asm>> implements AsmInstruction {}
class Label <<asm>> implements AsmInstruction {}
class Return <<asm>> implements AsmInstruction {}
class Block <<asm>> implements AsmInstruction {
  addTempVar(owner: AstItem, type: Symbol): AsmVar
}

Statement ..> AsmLinearFormResult : <<creates>>
Function *-- "0..*" AsmInstruction: instructions
Function *-- "0..*" Statement: statements
AsmLinearFormResult *.. "0..1" AsmValue: value
AsmLinearFormResult *.. "0..*" AsmInstruction: instructions
Block *.. AsmInstruction: instructions
```

> [!Note] Block Temporary Variables
> If needed an expression can register a temporary variable in the block where it's defined. Temporary variables from blocks are later merged into the function variables.

The linear form can add any instructions, including nested blocks, for things such as `for`, `if`, etc. They will be collapsed in the end, the variables extracted and merged to the parent function, and the instructions written in a serial fashion.