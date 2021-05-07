package com.teamdev.booby.impl;

import com.teamdev.booby.compiler.TypeBoobyExpression;
import com.teamdev.booby.compiler.statement.StatementCompiler;
import com.teamdev.booby.compiler.variable.InitVariableCompiler;
import com.teamdev.booby.compiler.procedure.ProcedureCompiler;
import com.teamdev.booby.compiler.program.ProgramCompiler;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.CompilerFactoryImpl;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class BoobyCompilerFactoryImpl implements CompilerFactory<TypeBoobyExpression, RuntimeEnvironment> {

    @Override
    public ElementCompiler<RuntimeEnvironment> create(TypeBoobyExpression type) {
        switch (type){
            case VARIABLE: return new InitVariableCompiler(new CompilerFactoryImpl());
            case PROCEDURE: return new ProcedureCompiler(new CompilerFactoryImpl());
            case PROGRAM: return new ProgramCompiler(new BoobyCompilerFactoryImpl());
            case STATEMENT: return new StatementCompiler(new BoobyCompilerFactoryImpl());
            default: throw new RuntimeException();
        }
    }
}
