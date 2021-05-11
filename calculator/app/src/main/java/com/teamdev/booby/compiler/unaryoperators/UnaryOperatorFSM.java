package com.teamdev.booby.compiler.unaryoperators;

import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class UnaryOperatorFSM extends FiniteStateMachine<StringBuilder> {

    private final DoubleOperatorSymbolState operatorSymbolState = new DoubleOperatorSymbolState();

    public UnaryOperatorFSM() {
        operatorSymbolState.addTransition(operatorSymbolState);
    }

    @Override
    protected List<State<StringBuilder>> getStartStates() {
        return Collections.singletonList(operatorSymbolState);
    }

    @Override
    protected List<State<StringBuilder>> getFinishStates() {
        return Collections.singletonList(operatorSymbolState);
    }
}
