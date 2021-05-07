package com.teamdev.booby.compiler.statement;

import com.teamdev.booby.compiler.TypeBoobyExpression;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class StatementFiniteStateMachine extends FiniteStateMachine<RuntimeEnvironment> {
    private final InitVariableState initVariableState;
    private final ProcedureState procedureState;

    public StatementFiniteStateMachine(CompilerFactory<TypeBoobyExpression, RuntimeEnvironment> compiler){

        initVariableState = new InitVariableState(compiler);
        procedureState = new ProcedureState(compiler);
    }

    @Override
    protected List<State<RuntimeEnvironment>> getStartStates() {
        return Arrays.asList(initVariableState, procedureState);
    }

    @Override
    protected List<State<RuntimeEnvironment>> getFinishStates() {
        return Arrays.asList(initVariableState, procedureState);
    }
}
