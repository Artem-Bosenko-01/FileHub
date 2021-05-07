package com.teamdev.calculator.compiler.booleanexpression;

import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;

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
