package com.teamdev.booby.compiler.unaryoperators.postfix;

import com.teamdev.booby.compiler.unaryoperators.UnaryOperandState;
import com.teamdev.booby.compiler.unaryoperators.UnaryOperatorState;
import com.teamdev.booby.runtime.UnaryOperatorOutputChain;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;

import java.util.Collections;
import java.util.List;

public class PostfixOperatorFiniteStateMachine extends FiniteStateMachine<UnaryOperatorOutputChain> {

    private final UnaryOperatorState unaryOperatorState = new UnaryOperatorState();
    private final UnaryOperandState operandState = new UnaryOperandState();

    public PostfixOperatorFiniteStateMachine(){
        operandState.addTransition(unaryOperatorState);
    }

    @Override
    protected List<State<UnaryOperatorOutputChain>> getStartStates() {
        return Collections.singletonList(operandState);
    }

    @Override
    protected List<State<UnaryOperatorOutputChain>> getFinishStates() {
        return Collections.singletonList(unaryOperatorState);
    }
}
