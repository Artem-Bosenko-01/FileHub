package com.teamdev.booby.compiler.fsm.statement;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;

import java.util.Arrays;
import java.util.List;

public class StatementFiniteStateMachine extends FiniteStateMachine<RuntimeEnvironment> {

    private final InitVariableState initVariableState = new InitVariableState();
    private final ProcedureState procedureState = new ProcedureState();

    public StatementFiniteStateMachine(){

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
