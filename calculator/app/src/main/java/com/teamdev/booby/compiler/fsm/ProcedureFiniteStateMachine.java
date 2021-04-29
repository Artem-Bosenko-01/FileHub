package com.teamdev.booby.compiler.fsm;

import com.teamdev.booby.runtime.ProcedureScope;
import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;

import java.util.List;

public class ProcedureFiniteStateMachine extends FiniteStateMachine<ProcedureScope> {
    NameStateForBooby

    public ProcedureFiniteStateMachine(){

    }

    @Override
    protected List<State<ProcedureScope>> getStartStates() {
        return null;
    }

    @Override
    protected List<State<ProcedureScope>> getFinishStates() {
        return null;
    }
}
