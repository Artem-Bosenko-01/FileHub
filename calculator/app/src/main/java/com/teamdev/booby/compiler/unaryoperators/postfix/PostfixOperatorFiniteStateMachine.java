package com.teamdev.booby.compiler.unaryoperators.postfix;

import com.teamdev.booby.compiler.unaryoperators.UnaryOperandState;
import com.teamdev.booby.compiler.unaryoperators.UnaryOperatorState;
import com.teamdev.booby.runtime.UnaryOperatorOutputChain;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * This is machine, that processes expression with postfix unary operator.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class PostfixOperatorFiniteStateMachine extends FiniteStateMachine<UnaryOperatorOutputChain> {

    private final UnaryOperatorState unaryOperatorState = new UnaryOperatorState();
    private final UnaryOperandState operandState = new UnaryOperandState();
    private final Logger logger = LoggerFactory.getLogger(PostfixOperatorFiniteStateMachine.class);

    public PostfixOperatorFiniteStateMachine(){
        logger.info("Start Postfix operator FSM");
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
