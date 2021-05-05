package com.teamdev.calculator.compiler.fsm.booleanexpression;

import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;

import java.util.Collections;
import java.util.List;

public class BooleanOperatorFiniteStateMachine extends FiniteStateMachine<StringBuilder> {

    private  final SymbolState symbolState = new SymbolState();

    public BooleanOperatorFiniteStateMachine(){
        symbolState.addTransition(symbolState);
    }

    @Override
    protected List<State<StringBuilder>> getStartStates() {
        return Collections.singletonList(symbolState);
    }

    @Override
    protected List<State<StringBuilder>> getFinishStates() {
        return Collections.singletonList(symbolState);
    }
}
