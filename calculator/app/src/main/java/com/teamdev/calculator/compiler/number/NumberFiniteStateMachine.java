package com.teamdev.calculator.compiler.number;


import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 *Number FSM - is {@link FiniteStateMachine machine}, that finds a number in
 * an {@link com.teamdev.calculator.compiler.InputCharacterStream input character stream}.
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class NumberFiniteStateMachine extends FiniteStateMachine<StringBuilder> {

    private final MinusState minusState = new MinusState();
    private final DigitState intState = new DigitState();
    private final DigitState decimalState = new DigitState();


    public NumberFiniteStateMachine() {
        Logger logger = LoggerFactory.getLogger(NumberFiniteStateMachine.class);
        logger.info("Create Number FSM");
        minusState.addTransition(intState);
        DotState dotState = new DotState();
        intState.addTransition(intState, dotState);
        dotState.addTransition(decimalState);
        decimalState.addTransition(decimalState);
    }


    @Override
    public List<State<StringBuilder>> getStartStates() {
        return Arrays.asList(minusState, intState);
    }

    @Override
    public List<State<StringBuilder>> getFinishStates() {
        return Arrays.asList(intState, decimalState);
    }
}
