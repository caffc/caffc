# CaffC

CaffC is a Java-like transpiler written itself in Java. It transpiles from CaffC files to C.

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

- **Run all tests**: `mvn test`
- **Run single test class**: `mvn test -Dtest=TestS001Instructions`
- **Run single test method**: `mvn test -Dtest=TestS001Instructions#basicSanityCheckForInstructionRendering`

Tests use JUnit 5 (JUnit Jupiter). Use the `CodeAssertsStr` utility class to compile CaffC code and assert on the generated C output. Use `CodeAssertsAst` for AST-level testing.

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
antlr4 -Dlanguage=Java --out-folder=src/main/java --package=com.germaniumhq.caffc.generated caffc.g4
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
| `isBlockStatement(obj)` | Returns true if obj is a block-level statement |
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

## Cursor/Copilot Rules

No Cursor or Copilot rules defined. Follow Java OpenJDK style and the project-specific conventions above.