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

The `xlC` profile uses the `ibmcom/xlc-ce` Docker image (ppc64le-only). On x86_64 hosts, `qemu-user-static` must be installed and up to date.

Docker profiles (`gcc_docker`, `xlC`, `clang_docker`, `gcc_asan_docker`) use a shared container per profile — container is started once, all tests run via `docker exec`, then container is stopped. The `docker_ready_command` config field can override the default `true` readiness probe (used by xlC which needs `which xlC` to wait for the compiler to initialize under QEMU emulation).

`gcc_docker` runs with libasan; `hello-world-native` is expected to fail there (asan detects an issue in the native block).

## Virtual Dispatch (Interfaces)

CaffC supports virtual dispatch through interface types via `_caffc_type_id` switch statements. When you call an interface method (e.g., `key.hash()` where `key` is `HasHash`), the compiler generates a call to the interface function (e.g., `caffc_HasHash_hash(key)`) which dispatches based on the object's `type_id`:

```c
// Generated by interface.peb template
caffc_i32 caffc_HasHash_hash(caffc_HasHash* _this) {
  switch (_this->_caffc_type_id) {
  case 15: return caffc_F64_hash(_this);
  case 10: return caffc_I32_hash(_this);
  case 39: return caffc_str_hash(_this);
  // ... more cases
  default: return 0;
  }
}
```

**Key rules:**
- A class must explicitly `implements HasHash` to be included in the dispatch switch. Just having a `hash()` method is NOT sufficient.
- `str` must declare `implements HasHash` to have its `hash()` and `equals()` methods reachable via virtual dispatch.
- Boxing classes (`I32`, `U16`, etc.) should also `implements HasHash` with `hash()` and `equals()` methods.
- The dispatch uses `switch (_this->_caffc_type_id)` — the concrete class type ID must match the registered type ID.

## Collections

Core collection interfaces and implementations live in `templates/common/default/caffc/collection.caffc`.

- `List<T>` — indexed access via `get(i32)/set(i32,T)/add/remove`
- `Dict<K is HasHash, V>` — key-value store using `K.hash()` and `K.equals()` virtual dispatch
- `Set<T is HasHash>` — unique items using `T.hash()` and `T.equals()` virtual dispatch
- All collections are `Iterable<T>`, enabling `for item in collection` syntax

Generic type restrictions (`K is HasHash`) resolve to the restriction type at compile time. The concrete type's `hash()`/`equals()` methods are called via virtual dispatch (interface functions with type_id switch).

**IMPORTANT:** Primitive types (`i32`, `u16`, etc.) CANNOT work as collection elements. In C, generic types are always pointers (`obj*` or whatever `is Something` resolves to). A raw `i32` value like `10` is NOT a pointer and will cause undefined behavior. Use boxing classes (`I32`, `U16`, etc.) for primitive values in collections.

## Arrays & Generics

The array system (`Module.ensureArray()`) resolves arrays at compile time:
- Primitives: `T_arr` (e.g., `u8_arr`, `i32_arr`, `u16_arr`, etc.) — use `#caffc_array("caffc_u8")` tag
- Non-primitives: `obj_arr` — every non-primitive `T[]` maps to `obj_arr*` in C
- `T[]` fields in generic classes generate `obj_arr* _items` in C; access via `T[]` CaffC syntax

`#caffc_array` tag marks classes as actual native arrays with flexible-size `_caffc_data[]` fields. It's only used for primitive arrays (`u8_arr`, `i32_arr`, `u16_arr`, `u32_arr`, `u64_arr`, `i8_arr`, `i16_arr`, `i64_arr`, `f32_arr`, `f64_arr`, `obj_arr`) and `obj_arr` itself. Generic class fields should use `T[]` syntax, not the tag.

## Boxing Classes

Primitive types need boxing classes to work with collections (which use object pointers). Boxing classes live in `templates/common/default/caffc/` as `{Type}_box.caffc`:

- `U8`, `I8`, `U16`, `I16`, `U32`, `I32`, `U64`, `I64`, `F32`, `F64`
- Each implements `HasHash` with `hash()` and `equals()` methods
- Each has a `value` field and a `to{Type}()` method
- Naming convention: capital letter for the boxing class (e.g., `U16` boxes `u16`)

## Primitive Arrays

All primitive array types are implemented in `templates/common/default/caffc/`:

- `u8_arr.caffc`, `i8_arr.caffc`, `u16_arr.caffc`, `i16_arr.caffc`, `u32_arr.caffc`, `i32_arr.caffc`, `u64_arr.caffc`, `i64_arr.caffc`, `f32_arr.caffc`, `f64_arr.caffc`
- Each follows the `u8_arr.caffc` template pattern with native blocks for `set`/`get`
- Each has a `_caffc_{type}_arr_size()` size calculator and `#caffc_array("caffc_{type}")` tag

## Index Access (`[]`)

`ExpressionIndexAccess` and `ExpressionAssign` resolve `[]` via `HasMethods` interface — any type with a `get()` method supports `[]` read, any type with `set()` supports `[]` write. This covers both native arrays and collection interfaces.

## For-In Loops

`for item in collection` syntax generates iterator-based while loops. The `ForInInstruction` AST node creates a synthetic iterator variable and emits calls to `newIterator()`, `hasNext()`, and `next()`. See `ForInInstruction.java` and `for_in.peb`.

## Global Variables and `unit_init`

Global initializers and optional per-file `unit_init()` bodies are moved into `module_init()` after type resolution:

- **C name**: globals are module-prefixed like functions (`i32 x` in module `main` → `main_x`)
- **Created/augmented** when global vars or any `unit_init` exist; skipped when neither is present
- Prefer an explicit `module_init()` in the module (unit tests should too — assert against that CU)
- **Order in generated C**: 1) global inits 2) original `module_init` code 3) each `unit_init` body (compilation-unit order)
- Each `unit_init` is transplanted into an `AsmBlock` then **deleted**; locals move onto `module_init`
- **`unit_init` is not registered** as a module function — calling it fails with `cannot resolve unit_init`
- **C signature**: `{module}_module_init()` (e.g. `yolo_module_init`)
- Header tests use the original unit path (header template renders the module)

## Switch Statements

Two forms (see `SwitchInstruction`):

1. **Boolean** — `switch { case cond: { ... } default: { ... } }` (no subject). Desugars to if/else-if/else. Cases must be `bool`.
2. **Value** — `switch x { case 3: { ... } default: { ... } }`. Primitives/arrays use `==`. Objects require `HasEquals` (`HasHash` extends it). `case null:` is allowed; a null subject does not call `equals` (no crash).

Bodies are block, `return`, or `break`/`continue` only (like `if`). No fall-through; `break` exits the switch. At least one `case`/`default`.

## Compile-time `#switch` / `#ifdef`

Evaluates against `caffc.yaml` / `BuildSettings` (not runtime). Unit-level or method
top-level only (not nested, not inside `if`/`while`/`for`). Inactive branches are never
type-resolved or codegen'd.

**`#switch`**: braced; first matching `#case` wins; no match + no `#default` → empty.

**`#ifdef`**: `#ifdef expr { … }` with optional `#else { … }`.

Settings: `gc.impl`, `gc.memory_trigger`, `debug.c_line_macro`, `debug.trace_line_runtime`,
`string.locale`/`impl`, `one_file`, `common.impl`, `exception.impl`, `i18n.impl`,
`i18n.files` (a fileset with `.contains("path")`). Expressions: literals, dotted settings,
numeric `+ - * /`/comparisons, string `==`/`!=`, `and`/`or`/`not`, and
`i18n.files.contains("…")`.

```caffc
#switch {
#case gc.impl == "default" and 1 + 2 > 2: { /* kept */ }
#default: { /* fallback */ }
}

#ifdef i18n.files.contains("generated/iso/CodePage8859_2.caffc") {
  unit_init() { /* register code page */ }
}
```

### `i18n` feature + `files` globs

Template package: `templates/i18n/{impl}/caffc/` (same layout as exception/gc/string).
Single-byte code pages live under `caffc/generated/<vendor>/` (`iso`, `microsoft`,
`apple`, `misc`, `next`, `iana`). Data comes from the local mirror
`/home/raptor/learn/projects/caffc/unicode` (unicode.org MAPPINGS + IANA
`charset-reg`). Regenerate with `python3 util/generate-codepages.py`
(multi-byte encodings are skipped).
`caffc.yaml`:

```yaml
i18n:
  files:
    includes:
      - glob("generated/iso/CodePage8859_2.caffc")
      - glob("generated/microsoft/CodePage1252.caffc")
    excludes:
      - glob("generated/**/*WIP*")
```

`glob("pattern")` entries (or plain patterns) use Java/bazel-style globs. Defaults to
**no** generated code pages — opt in via `includes`. Code pages register themselves via
`unit_init()` guarded by `#ifdef i18n.files.contains("…")`; look them up with
`caffc.i18n.getCodePage`. Registry `name()` values are short ids for unicode.org
vendors (`8859-2`, `1252`, `mac-roman`) and IANA charset names for `iana/`
(`windows-1252`, `Amiga-1251`, …); `vendor()` is `ISO` / `Microsoft` / `Apple` /
`IANA` / …

## Gotchas
- **Blank identifier `_`** — Go-style discard on assign LHS only (`x, _, _ = cp.fromUtf8(...)`, or `_ = expr`). Not a real variable; do not declare or read `_`. Discarded object/array multi-return slots are still zero-cleared for GC.
- **Globals are module-prefixed in C** — `i32 x` in module `main` becomes `main_x`; dots in module names become underscores (`caffc.i18n` → `caffc_i18n_x`). Native blocks must use the C name if they touch globals.
- **`#switch` / `#ifdef` are compile-time** — `BuildSettings` only; no nesting / mid-control-flow.
- **`continue` not supported** — avoid `continue` in while loops. Use nested if/return instead.
- **No modulo (`%`)** — only `+`, `-`, `*`, `/` are supported for math. Use bitwise AND (`&`) for modular arithmetic with power-of-2 values.
- **Bitwise vs boolean** — bitwise ops use C syntax (`&`, `|`, `^`, `~`, `<<`, `>>`); boolean ops use words (`and`, `or`, `not`).
- **No local shadowing** — locals cannot be redeclared in nested blocks; declare once at function scope and assign.
- **Array `.size` is a field** — use `arr.size` (or `(i32) arr.size`), not `arr.size()`. `str.size()` is a method.
- **`instanceof` syntax** — use `x not instanceof Y` (not `not x instanceof Y`).
- **Exception / null returns** — handlers return `0` for primitives and `null` for objects; never return `null` for primitives (`f32`/`f64` etc.).
- **`implements HasHash` required for virtual dispatch** — `str` and boxing classes must declare it; a matching `hash()`/`equals()` alone is not enough. `HasHash extends HasEquals`.
- **Nested feature templates + multi-return** — template `.caffc` files are discovered recursively; multi-return structs are `{Owner}_{fn}_structreturn`, and interface implementors reuse the interface return struct for C type matching.
- **`unit_init()` is not callable** — never registered as a module function (`cannot resolve`); inlined into `module_init` then deleted; no parameters or return type.
- **Switch case syntax** — both forms require a colon after the case expression / `default` (`case cond: {`, `case 3: {`, `default: {`).
- **Java `**` globs** — `generated/**/*.caffc` does not match files directly under `generated/`; also list `generated/*.caffc` when needed.
