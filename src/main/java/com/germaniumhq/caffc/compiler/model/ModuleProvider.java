package com.germaniumhq.caffc.compiler.model;

/**
 * Gets a module for the given name
 */
public interface ModuleProvider {
    Module getModule(String moduleName);
}
