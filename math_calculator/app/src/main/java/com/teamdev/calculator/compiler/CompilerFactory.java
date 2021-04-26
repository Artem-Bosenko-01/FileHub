package com.teamdev.calculator.compiler;

import com.teamdev.calculator.runtime.ShuntingYardStack;

public interface CompilerFactory {
    ElementCompiler<ShuntingYardStack> create(TypeOfExpressionElement type);
}
