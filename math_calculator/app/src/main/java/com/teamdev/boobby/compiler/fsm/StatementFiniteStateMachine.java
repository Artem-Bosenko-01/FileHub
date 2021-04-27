package com.teamdev.boobby.compiler.fsm;

import com.teamdev.boobby.runtime.ResultScope;
import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;

import java.util.List;

public class StatementFiniteStateMachine extends FiniteStateMachine<ResultScope> {

    public StatementFiniteStateMachine(){

    }

    @Override
    public List<State<ResultScope>> getStartStates() {
        return null;
    }

    @Override
    public List<State<ResultScope>> getFinishStates() {
        return null;
    }
}
