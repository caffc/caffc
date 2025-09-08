The compiler works in a few phases:
1. **Loading of the compilation units.** Currently all of them are loaded in memory. Antlr is used to read an antlr specific AST.  Antlr does both the lexing and parsing in one step. This _antlr specific AST_ is converted to our _model AST_. This internal _model AST_ is what gets used for rendering as models in the template.
2. **Resolving of the types.** The code has references to things that might not exist, are behind generics, are aliased, etc. For all of these, we need to find the actual things that are behind. `SymbolSearch` objects are used to resolve 
## TypeName

As the name implies, represents the typeName of something. The typeName has:
* module
* clazz (optional)
* name
* dataType

The dataType in turn says what kind of data the typeName represents. In case it represents an object that's not yet resolvable its dataType will be `UNRESOLVED` until is registered.

## Expression
An expression represents one or more instructions that do something with variables and operators. Expressions also have a typeName, and the typeName can be derived from the operator itself. For example, an `i8` + `i16` variable will yield an `i16` variable.

## AstItem
The compiler parses the ANTLR AST nodes into internal `AstItem` instances. The `AstItem` interface exists to allow navigating up the scopes of the functions, classes, etc. when having to resolve variables.

To quickly navigate to the first parent with the given class, we can call `findAstParent` within the class.

## Scope
A scope represents a container for names, that will be resolved to `Symbol`s from the AST. For example the local variable `i` will be resolved by a `for` scope, as a Local variable that will return `i16` as typeName and of course its name `i`.

All global variables, functions and their statements (not definitions!), etc. are stored as close as possible to their owner (in the class that defines them, or module, etc).
* `Program`
* `Module`
* `CompilationUnit`
* `Clazz`
* `ClassDefinition`
* `Function`
## Symbol
A symbol represents a resolved name in the context of a Scope. A Symbol has a `name` and a `typeName`. Symbols can be resolved from a given `AstItem` by its name and generics definition, using a `SymbolSearch` object via a `SymbolResolver`.

* `ClassDefinition`
* `Field`
* `FunctionDefinition`
* `Module`
* `Parameter`
* `PrimitiveSymbol`
* `VariableDeclaration` *!**FIXME**: this should not be an AST item*

## Program Structure

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

class ClassDefinition <<Symbol>> {
  + name: str
  + typeName: Type
  + gcFieldsCount: int
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
Module *-- "0..*" ClassDefinition
Module *-- "0..*" FunctionDefinition
Module o-- "0..*" Module: <<usedModules>>
ClassDefinition *-- "0..*" FunctionDefinition : functions
ClassDefinition *-- "0..*" Field : fields
FunctionDefinition *-- Parameter
ClassDefinition *-- GenericDefinitions
FunctionDefinition *-- GenericDefinitions
GenericDefinitions *-- "0..*" GenericDefinition: definitions
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

Function *-- "0..*" Statement: statements
Program *-- "2..*" Module
Module o-- "1..*" CompilationUnit
CompilationUnit *-- "0..*" CompileBlock
```

## Symbol Resolving
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
The resolving can return types that aren't fully resolved, but that's OK, since the typeName restrictions come into effect, and that's enough:
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
A<CustomItem> a = new A<CustomItem>()  // here we don't know the typeName of T, but it's ok
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


