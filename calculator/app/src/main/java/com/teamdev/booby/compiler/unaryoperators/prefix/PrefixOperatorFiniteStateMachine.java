package com.teamdev.booby.compiler.unaryoperators.prefix;

import com.teamdev.booby.compiler.unaryoperators.UnaryOperandState;
import com.teamdev.booby.compiler.unaryoperators.UnaryOperatorState;
import com.teamdev.booby.runtime.UnaryOperatorOutputChain;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.booleanexpression.SymbolState;

import java.util.Collections;
import java.util.List;

public class PrefixOperatorFiniteStateMachine extends FiniteStateMachine<UnaryOperatorOutputChain> {

    private final UnaryOperatorState unaryOperatorState = new UnaryOperatorState();
    private final UnaryOperandState operandState = new UnaryOperandState();

    public PrefixOperatorFiniteStateMachine(){
        unaryOperatorState.addTransition(operandState);
    }

    @Override
    protected List<State<UnaryOperatorOutputChain>> getStartStates() {
        return Collections.singletonList(unaryOperatorState);
    }

    @Override
    protected List<State<UnaryOperatorOutputChain>> getFinishStates() {
        return Collections.singletonList(operandState);
    }
}
