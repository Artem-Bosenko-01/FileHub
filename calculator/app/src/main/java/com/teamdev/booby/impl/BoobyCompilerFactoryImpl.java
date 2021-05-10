package com.teamdev.booby.impl;

import com.teamdev.booby.compiler.statement.StatementCompiler;
import com.teamdev.booby.compiler.variable.InitVariableCompiler;
import com.teamdev.booby.compiler.procedure.ProcedureCompiler;
import com.teamdev.booby.compiler.program.ProgramCompiler;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.impl.CompilerFactoryImpl;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;

/**
 * This is implementation compiler factory for {@link com.teamdev.booby.Booby booby compiler},
 * that return type of {@link ElementCompiler compiler} for appropriate
 * {@link TypeOfExpressionElement type of expression}.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class BoobyCompilerFactoryImpl implements CompilerFactory<RuntimeEnvironment> {

    @Override
    public ElementCompiler<RuntimeEnvironment> create(TypeOfExpressionElement type) {
        switch (type){
            case VARIABLE: return new InitVariableCompiler(new CompilerFactoryImpl());
            case PROCEDURE: return new ProcedureCompiler(new CompilerFactoryImpl());
            case PROGRAM: return new ProgramCompiler(new BoobyCompilerFactoryImpl());
            case STATEMENT: return new StatementCompiler(new BoobyCompilerFactoryImpl());
            default: throw new RuntimeException();
        }
    }
}
