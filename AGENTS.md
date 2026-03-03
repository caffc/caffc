# CaffC

CaffC is a Java-like transpiler written itself in Java. It transpiles from CaffC files to C.

The code is parsed in several stages:

1. the source is tokenized + parsed into an abstract syntax tree (AST) by antlr (the grammar it's in `caffc.g4`)
2. the Antlr AST is converted to an internal AST representation (`com.germaniumhq.caffc.compiler.model.*`)
3. The internal AST is converted to a linear form of assembler-like instructions (`com.germaniumhq.caffc.compiler.asm.*`)
4. The linear form is then rendered using pebble templates into the actual files.

All of this is visible in the `com.germaniumhq.caffc.compiler.MainApp` class. The same ideas are replicated in the unit test execution `com.germaniumhq.caffc.CodeAssertsStr` - see for example `compileCaffcProgram`.

## Sources

This is mostly a regular maven project.

The pebbles templates are in `src/main/resources/caffc/templates/c`.

It's possible to switch implementation of core features such as strings, gc implementation, exceptions, etc. This is visible in `templates/*`.

More details on the class hierarchy is available in the `compiler.md` document
