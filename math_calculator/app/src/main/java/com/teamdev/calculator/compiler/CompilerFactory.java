package com.teamdev.calculator.compiler;

public interface CompilerFactory {
    ElementCompiler create(TypeOfExpressionElement type);
}
