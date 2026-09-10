# CaffC

CaffC is a Java-like transpiler written itself in Java. It transpiles from CaffC files to C.

NOTE: Feel free to update this file with any gotchas you run into, or important implementation details, that will help you be faster in the future. Keep this file under 300 lines. If getting near this, try to restructure this file better.

## CaffC Rules

- Unlike Java classes cannot be inherited in CaffC. This means that effectively every class is `final` by default.
- Strings (see `str.caffc`) are stored natively as nul-terminated UTF-8 strings in memory.

## Parsing

The code is parsed in several stages:

1. the source is tokenized + parsed into an abstract syntax tree (AST) by antlr (the grammar it's in `caffc.g4`)
2. the Antlr AST is converted to an internal AST representation (`com.germaniumhq.caffc.compiler.model.*`)
3. The internal AST is converted to a linear form of assembler-like instructions (`com.germaniumhq.caffc.compiler.asm.*`)
4. The linear form is then rendered using pebble templates into the actual files.

All of this is visible in the `com.germaniumhq.caffc.compiler.MainApp` class. The same ideas are replicated in the unit test execution `com.germaniumhq.caffc.CodeAssertsStr` - see for example `compileCaffcProgram`.

## Build Commands

This is a Maven project using Java 17.

- **Build**: `mvn clean package`
- **Generate fatjar**: `mvn clean package` (uses maven-shade-plugin)
- **Run compiler**: `bin/caffc <args>` (shell script wrapper for the jar)

### Tests

NOTE: Always use `mvn` to run the unit tests.

- **Run all tests**: `mvn test`
- **Run single test class**: `mvn test -Dtest=TestS001Instructions`
- **Run single test method**: `mvn test -Dtest=TestS001Instructions#basicSanityCheckForInstructionRendering`

Tests use JUnit 5 (JUnit Jupiter). Use the `CodeAssertsStr` utility class to compile CaffC code and assert on the generated C output. Use `CodeAssertsAst` for AST-level testing.

#### Main Test Checks

Tests use the following functions:
- `compileCaffcProgram`: compiles just the given compilation units, without any system library. Good for tests that only check AST parsing and minimal code generation. Interfaces such as `obj` must be defined in the test.
- `compileFullCaffcProgram`: compiles the given compilation units, with all the system libraries. Good for testing actual language integrations (i.e. for `str`, or in the future collections)

NOTE: Test output parsing strips out `#line` preprocessor macros.

### Integration Tests (`caffc-tests/`)

The `caffc-tests/` directory contains integration tests that compile and execute real CaffC programs through the full pipeline (caffc -> gcc -> run).

**Test runner**: `caffc-tests/run-tests.py` (Python 3, no external dependencies)

```bash
# Run all integration tests (default profile: gcc)
cd caffc-tests && python3 run-tests.py

# Run with specific profile
python3 run-tests.py --profile clang

# Run specific tests
python3 run-tests.py hello-world-caffc string-tests

# Run with multiple profiles (matrix mode)
python3 run-tests.py --profile gcc --profile clang

# Override flags
python3 run-tests.py --compile-flags "-O2" --extra-libs "-lm"

# Dry-run / fail-fast / parallel
python3 run-tests.py --dry-run --fail-fast -j 4
```

Profiles (built-in: gcc, clang, c89, gcc_release, gcc_valgrind, gcc_asan, gcc_tsan) are loaded from `caffc-tests/profiles/*.yaml`. Per-project overrides use `run-tests-config.yaml` in each test directory. See `caffc-tests/README.md` for details.

## Code Style Guidelines

### Java

- **Java version**: 17
- **Imports**: Group standard, external, and project imports separately. Within groups, sort alphabetically.
- **Formatting**: Use 4 spaces per indentation level (configure `.editorconfig` for Java: `indent_size = 4`)
- **Naming**:
  - Classes: `PascalCase` (e.g., `MainApp`, `Expression`)
  - Methods: `camelCase` (e.g., `compileCaffcProgram`, `recurseResolveTypes`)
  - Variables/fields: `camelCase` (e.g., `compilationUnit`, `inputSources`)
  - Constants: `UPPER_SNAKE_CASE` (e.g., `CAFFC_HOME`)
  - Interfaces: Prefix with `I` or use `PascalCase` (e.g., `Statement`, `Expression`)
- **Error handling**: UseChecked exceptions are rare; prefer returning null or throwing unchecked exceptions. Error messages go to `CaffcCompiler.get().fatal()` or `CaffcCompiler.get().error()`.
- **Package structure**:
  - `com.germaniumhq.caffc.compiler.model.*`: Internal AST nodes
  - `com.germaniumhq.caffc.compiler.asm.*`: Linear instruction form
  - `com.germaniumhq.caffc.compiler.optimizer.*`: Optimization passes
  - `com.germaniumhq.caffc.output.*`: Template rendering
  - `com.germaniumhq.caffc.args.*`: Command-line argument parsing
  - `com.germaniumhq.caffc.generated.*`: ANTLR-generated parser classes

### CaffC Language Files

- **Extension**: `.caffc`
- **Indentation**: 2 spaces (as configured in `.editorconfig`)
- **Files**: `templates/feature/profile/caffc/` contains core library implementations

### General

- Use LF line endings (configured in `.editorconfig`)
- Add final newline to Java files, omit for `.caffc` files
- No trailing whitespace

## ANTLR Grammar

The grammar is in `caffc.g4`. After changes, regenerate parser code:
```bash
# Download ANTLR (first time only)
curl -o antlr4.jar https://www.antlr.org/download/antlr-4.13.1-complete.jar

# Regenerate parser
java -jar antlr4.jar -o src/main/java/com/germaniumhq/caffc/generated -package com.germaniumhq.caffc.generated -no-listener -no-visitor caffc.g4
```

## Template Engine

Uses Pebble templates (`*.peb` files) for code generation. Templates are in:
- `src/main/resources/caffc/templates/c/`: C code templates
- `templates/feature/profile/`: Feature implementations (gc, string, exception, common)

### Template Context

Templates receive objects like `Module`, `CompilationUnit`, `Function`, `Field`, etc. Use `|c_name` filter to get C name representations.

### Template Filters

The following filters are available in Pebble templates (mapped in `CaffcPebblesExtension`):

| Filter | Rendered Text |
|--------|-------------|
| `c_header` | The corresponding C header file for the Module |
| `c_global_header` | Global header guard wrapper |
| `c_header_guard` | Header guard macro name |
| `c_function_signature` | C function signature with parameters |
| `c_resolve_id` | Resolve identifier from context |
| `c_name` | C type/identifier name (e.g., `caffc_i32`) |
| `c_type_name` | C type name without pointer (always) |
| `c_type` | C type with pointer markers for non-primitives |
| `c_constructor_parameters` | Constructor parameters (skips `_this`) |
| `c_constructor_parameters_call` | Constructor call arguments |
| `semicolon` | Add semicolon unless context is blocks, labels, comments, or jumps |
| `render` | Render nested template for object based on type mapping |

### Template Functions

| Function | Description |
|----------|-------------|
| `isBlockStatement(obj)` | Returns true if obj is a block-level statement - doesn't need a semicolon at the end |
| `isIndex(obj)` | Returns true if obj represents an array index |
| `get(collection, key)` | Get item from list/map by key/index |

### Template Context Objects

Common objects passed to templates:
- `Module` - Module container with functions, classes, and string constants
- `CompilationUnit` - Single source file with module, imports, and AST nodes
- `Function` - Function with statements and variables
- `ClassDefinition` / `InterfaceDefinition` / `Struct` - Type definitions
- `Field`, `Parameter` - Variable definitions
- `AsmBlock`, `AsmInstruction` - Linear assembly instructions
- `Expression` subclasses - All expression types (math, bool, bit, ternary, etc.)

### Debugging

Set `CAFFC_DEBUG_TEMPLATES` environment variable to see template names in output.

## xlC Profile (ppc64le Docker)

`xlC` uses `ibmcom/xlc-ce` (ppc64le; needs `qemu-user-static` on x86_64). Docker profiles share one container per profile (`docker_ready_command` overrides readiness; xlC uses `which xlC`). `gcc_docker` has libasan — `hello-world-native` is expected to fail there.

## Virtual Dispatch (Interfaces)

Interface calls become `switch (_this->_caffc_type_id)` in the interface function (see `interface.peb`). A class must explicitly `implements HasHash` (etc.) to be included — a matching method alone is not enough. Same for `str` and boxing classes. `HasHash extends HasEquals`.

## Collections / arrays / boxing

`templates/common/default/caffc/collection.caffc`: `List<T>`, `Dict<K is HasHash, V>`, `Set<T is HasHash>` (all `Iterable<T>`). Primitives cannot be collection elements — use boxing (`I32`, `U16`, … in `*_box.caffc`).

Arrays (`Module.ensureArray()`): primitives → `T_arr` + `#caffc_array("caffc_T")`; non-primitives → `obj_arr`. Generic fields use `T[]`, not the tag. `[]` lowers via `get`/`set` (`HasMethods`). `for item in collection` → iterator while (`ForInInstruction`).

## Global Variables and `unit_init`

Global inits and `unit_init()` bodies move into `module_init()` after resolve (skip if neither exists). Order: global inits → original `module_init` → each `unit_init` (CU order). `unit_init` is deleted after transplant and is not callable. C name: `{module}_module_init()`; globals are module-prefixed (`main_x`).

## Switch Statements

1. **Boolean** — `switch { case cond: { … } default: { … } }` → if/else-if/else (`bool` cases).
2. **Value** — `switch x { case 3: { … } }`. Objects need `HasEquals`; `case null:` is safe. No fall-through; bodies are block/`return`/`break`/`continue`.

## Compile-time `#switch` / `#ifdef`

Against `BuildSettings` only; unit- or method-top-level. Inactive branches are not resolved/codegen'd. Settings include `gc.*`, `i18n.files.contains("…")`, etc. See `#case` example below and `i18n` note after defaults.

```caffc
#switch {
#case gc.impl == "default" and 1 + 2 > 2: { /* kept */ }
#default: { /* fallback */ }
}

#ifdef i18n.files.contains("generated/iso/CodePage8859_2.caffc") {
  unit_init() { /* register code page */ }
}
```

## Default parameters, named args, varargs

```caffc
hello(i32 a=3, i32 b=7) { }
hello(b=9)                 // defaults inlined before call

pack(i32 a ... obj[] rest) { }
pack(1, null, null)        // rest packed into new array

// Trailing array without `...`: exact arity passes through; extras pack
pack2(i32 a, obj[] rest) { }

// `... array, Dict` → varargs + kwargs (unknown names → dict)
flex(i32 a ... obj[] rest, Dict<str, obj> kw) { }
flex(1, extra=null)
```

`FunctionCallBinder` + `ExpressionArrayPack` / `ExpressionKwargsPack`. Explicit `...` (or `name...`) always packs leftovers; pass an existing array by name (`rest=xs`).

**i18n files:** `templates/i18n/{impl}/caffc/`; `caffc.yaml` `i18n.files` globs (default none); regenerate via `python3 util/generate-codepages.py`.

## Gotchas
- **Blank identifier `_`** — Go-style discard on assign LHS only. Not a real variable; discarded object/array multi-return slots are still GC zero-cleared.
- **Globals are module-prefixed in C** — `main_x`; dots → underscores. Native blocks must use the C name.
- **`#switch` / `#ifdef` are compile-time** — `BuildSettings` only; no nesting / mid-control-flow.
- **`continue` not supported** — use nested if/return instead.
- **No modulo (`%`)** — use `&` for power-of-2 modular arithmetic.
- **Bitwise vs boolean** — `& | ^ ~ << >>` vs `and` / `or` / `not`.
- **No local shadowing** — declare once at function scope and assign.
- **Array `.size` is a field** — not `arr.size()`. `str.size()` is a method.
- **`instanceof` syntax** — `x not instanceof Y` (not `not x instanceof Y`).
- **Exception / null returns** — `0` for primitives, `null` for objects.
- **`implements HasHash` required for virtual dispatch** — matching `hash()`/`equals()` alone is not enough.
- **Multi-return structs** — `{Owner}_{fn}_structreturn`; interface implementors reuse the interface return struct.
- **`unit_init()` is not callable** — inlined into `module_init` then deleted.
- **Switch cases need a colon** — `case cond: {`, `default: {`.
- **Java `**` globs** — `generated/**/*.caffc` misses files directly under `generated/`.
- **Call arity is checked** — missing required args / excess positionals (non-varargs) are errors; no positional after named.
- **Generic interface copies keep parents** — `Dict<K,V>` / `List<T>` instantiate via `InterfaceDefinition.newGenericsCopy`, which must retain `extends` parents so inherited methods (`size()`, etc.) resolve. Prefer `Dict<str, obj>` for kwargs params; packing still builds a concrete `HashDict`.
- **Interface dispatch uses canonical definitions** — `implements` registration walks parent interfaces but must register on the module's base `InterfaceDefinition` (not generics copies), or parent dispatchers like `Collection_size` stay empty. `interface.peb` only emits cases for classes that define the method (HashDict has `size` but not `newIterator` yet).
