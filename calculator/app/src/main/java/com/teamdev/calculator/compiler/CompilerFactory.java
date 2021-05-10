package com.teamdev.calculator.compiler;

/**
 *This is API for realization
 * <a href="https://www.geeksforgeeks.org/factory-method-design-pattern-in-java/">factory pattern</a>
 * to create {@link ElementCompiler one of element compiler}
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyDependents"})
public interface CompilerFactory<RESULT> {
    ElementCompiler<RESULT> create(TypeOfExpressionElement type);
}
