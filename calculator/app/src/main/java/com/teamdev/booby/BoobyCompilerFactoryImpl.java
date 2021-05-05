package com.teamdev.booby;

import com.teamdev.booby.compiler.TypeBoobyExpression;
import com.teamdev.booby.compiler.fsm.variable.InitVariableCompiler;
import com.teamdev.booby.compiler.fsm.procedure.ProcedureCompiler;
import com.teamdev.booby.compiler.fsm.program.ProgramCompiler;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;

public class BoobyCompilerFactoryImpl implements CompilerFactory<TypeBoobyExpression, RuntimeEnvironment> {

    @Override
    public ElementCompiler<RuntimeEnvironment> create(TypeBoobyExpression type) {
        switch (type){
            case VARIABLE: return new InitVariableCompiler();
            case PROCEDURE: return new ProcedureCompiler();
            case PROGRAM: return new ProgramCompiler();
            default: throw new RuntimeException();
        }
    }
}
