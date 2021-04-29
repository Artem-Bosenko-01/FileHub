package com.teamdev.calculator.compiler.fsm.number;


import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 *Number FSM - is {@link FiniteStateMachine machine}, that finds a number in
 * an {@link com.teamdev.calculator.compiler.InputCharacterStream input character stream}.
 */
public class NumberFiniteStateMachine extends FiniteStateMachine<StringBuilder> {
    private final Logger logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(NumberFiniteStateMachine.class);

    private final MinusState minusState = new MinusState();
    private final DigitState intState = new DigitState();
    private final DotState dotState = new DotState();
    private final DigitState decimalState = new DigitState();


    public NumberFiniteStateMachine() throws InvalidSymbolException {
        logger.info("Create Number FSM");
        minusState.addTransition(intState);
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
