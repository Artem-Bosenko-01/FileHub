package com.teamdev.calculator.compiler;

import com.teamdev.calculator.runtime.ShuntingYardStack;

/**
 *This is API for realization
 * <a href="https://www.geeksforgeeks.org/factory-method-design-pattern-in-java/">factory pattern</a>
 * to create {@link ElementCompiler one of element compiler}
 * */
public interface CompilerFactory {
    ElementCompiler<ShuntingYardStack> create(TypeOfExpressionElement type);
}
