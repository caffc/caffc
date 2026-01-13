package com.germaniumhq.caffc.compiler.model;

import com.germaniumhq.caffc.compiler.error.CaffcCompiler;
import com.germaniumhq.caffc.compiler.model.source.SourceItem;
import com.germaniumhq.caffc.compiler.model.type.Scope;
import com.germaniumhq.caffc.compiler.model.type.Symbol;
import com.germaniumhq.caffc.compiler.model.type.TypeName;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * A program contains a reference to all the modules loaded. Note that a program
 * must not be fully loaded for the compiler to work, but only the desired
 * modules. At some point, the module definitions should be cached locally,
 * we don't need to always parse caffc code to know the definitions of things.
 */
public class Program implements ModuleProvider, AstItem, Scope {
    public static Program INSTANCE = new Program();

    public Map<String, Module> modules = new HashMap<>();
    public Map<TypeName, Symbol> registeredTypes = new HashMap<>();

    public String astFilePath;
    public int astColumn;
    public int astLine;

    public long labelIndex;

    // Keeps track where something is defined. We have two kind of mappings kept in sync:
    //
    // name -> file.location:line
    //
    // and
    //
    // file.location -> [names]
    //
    // The rationale is that when we add a new function we can see if we already have it defined.
    // If we do, we can report to the user the locations of where this happens and report this
    // as errors.
    //
    // If a file is being removed, or recompiled, we can clear up the nameToLocation cache
    // faster.
    public Map<String, String> nameToLocation = new HashMap<>(); // name -> file.location:line
    public Map<String, Set<String>> locationToNames = new HashMap<>(); // file.location -> [names]

    public List<TypeResolveRequest> typeResolveRequests = new ArrayList<>();
    private Map<TypeName, Symbol> primitiveSymbols = new HashMap<>();

    private final MessageDigest sha256Digest;
    public Map<String, StringConstant> stringConstantsMap = new LinkedHashMap<>();

    private Program() {
        try {
            this.sha256Digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            CaffcCompiler.get().fatal(
                    SourceItem.fromFilePath("?"),
                    "sha256 issue: " + e.getMessage());
            throw new RuntimeException(e); // not reached
        }

        for (TypeName primitiveTypeName : TypeName.PRIMITIVE_TYPES) {
            primitiveSymbols.put(primitiveTypeName, new TypeSymbol(primitiveTypeName));
        }
    }

    @Override
    public Module getModule(String moduleName) {
        Module result = modules.computeIfAbsent(moduleName, Module::new);
        result.program = this;

        register(result.typeName(), result);

        return result;
    }

    /**
     * The program is the toplevel object.
     * @return
     */
    @Override
    public AstItem getOwner() {
        return null;
    }

    /**
     * Register a definition of a type to a specific symbol. A symbol is a link to
     * the full definition of the given type. For now a ClassDefinition, or a
     * FunctionDefinition, since we need to match their signatures.
     *
     * @param typeName
     * @param item
     */
    public void register(TypeName typeName, Symbol item) {
        this.registeredTypes.put(typeName, item);
    }

    public Symbol getTypeDefinition(TypeName typeName) {
        return registeredTypes.get(typeName);
    }

    /**
     * Resolve all the type requests
     */
    public void recreateConstants() {
        recreateStringConstants();
    }

    private void recreateStringConstants() {
        this.stringConstantsMap.clear();

        for (Module module: modules.values()) {
            for (String stringConstant: module.stringConstants) {
                stringConstantsMap.computeIfAbsent(stringConstant, this::newStringConstant);
            }
        }
    }

    private StringConstant newStringConstant(String it) {
        StringConstant stringConstant = new StringConstant();

        // removes the surrounding quotes
        String actualStr = it.substring(1, it.length() - 1);
        byte[] bytes = actualStr.getBytes(StandardCharsets.UTF_8);
        stringConstant.name = "caffc_cstr_" + bytesToHex(sha256Digest.digest(bytes));
        stringConstant.bytes = bytes;
        stringConstant.bytesSize = bytes.length + 1; // we add the null terminator

        return stringConstant;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private String extractNonArrayTypeName(String name) {
        while (name.endsWith("[]")) {
            name = name.substring(0, name.length() - 2);
        }

        return name;
    }

    private int extractArrayLevel(String name) {
        int level = 0;

        while (name.endsWith("[]")) {
            name = name.substring(0, name.length() - 2);
            level++;
        }

        return level;
    }

    /**
     * Adds an item to be type resolved after all the AST trees are parsed.
     */
    public TypeName requestTypeResolve(AstItem owner, TypeName typeName) {
        TypeName result = TypeName.copyOf(typeName);
        TypeResolveRequest request = new TypeResolveRequest(owner, result);

        typeResolveRequests.add(request);

        return result;
    }

    public Collection<Integer> strStructBytesSizes() {
        TreeSet<Integer> sizes = new TreeSet<>();

        for (StringConstant stringConstant: stringConstantsMap.values()) {
            sizes.add(stringConstant.bytesSize);
        }

        return sizes;
    }

    @Override
    public Symbol resolve(String name) {
        if (modules.containsKey(name)) {
            return modules.get(name);
        }

        TypeName primitive = TypeName.primitiveType(name);
        if (primitive != null) {
            return primitiveSymbols.get(primitive);
        }

        return null;
    }

    public Collection<StringConstant> stringConstants() {
        return stringConstantsMap.values();
    }

    public String constantName(String value) {
        return stringConstantsMap.get(value).name;
    }

    @Override
    public String getFilePath() {
        return astFilePath;
    }

    @Override
    public int getLineNumber() {
        return astLine;
    }

    @Override
    public int getColumnNumber() {
        return astColumn;
    }

    @Override
    public void recurseResolveTypes() {
        throw new IllegalStateException("BUG: recurse resolve types can only start at compilation unit level");
    }

    public static Program get() {
        return INSTANCE;
    }

    public static Program reset() {
        INSTANCE = new Program();

        return INSTANCE;
    }
}
