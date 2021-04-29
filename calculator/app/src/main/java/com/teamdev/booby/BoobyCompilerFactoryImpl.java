package com.teamdev.booby;

import com.teamdev.booby.compiler.TypeBoobyExpression;
import com.teamdev.booby.compiler.impl.InitVariableCompiler;
import com.teamdev.booby.compiler.impl.ProcedureCompiler;
import com.teamdev.booby.compiler.impl.ProgramCompiler;
import com.teamdev.booby.compiler.impl.StatementCompiler;
import com.teamdev.booby.runtime.ResultScope;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;

public class BoobyCompilerFactoryImpl implements CompilerFactory<TypeBoobyExpression, ResultScope> {

    @Override
    public ElementCompiler<ResultScope> create(TypeBoobyExpression type) {
        switch (type){
            case VARIABLE: return new InitVariableCompiler();
            case PROCEDURE: return new ProcedureCompiler();
            case STATEMENT: return new StatementCompiler();
            case PROGRAM: return new ProgramCompiler();
            default: throw new RuntimeException();
        }
    }
}
