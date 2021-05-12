package com.teamdev.booby.compiler.unaryoperators.prefix;

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
 * This is machine, that processes expression with prefix unary operator.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class PrefixOperatorFiniteStateMachine extends FiniteStateMachine<UnaryOperatorOutputChain> {

    private final UnaryOperatorState unaryOperatorState = new UnaryOperatorState();
    private final UnaryOperandState operandState = new UnaryOperandState();
    private final Logger logger = LoggerFactory.getLogger(PrefixOperatorFiniteStateMachine.class);

    public PrefixOperatorFiniteStateMachine(){
        logger.info("Start Prefix operator FSM");
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
