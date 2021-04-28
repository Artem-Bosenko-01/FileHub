package com.teamdev.calculator.compiler.fsm.number;


import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 *Number FSM - is {@link FiniteStateMachine machine}, that finds a number in
 * an {@link com.teamdev.calculator.compiler.InputCharacterStream input character stream}.
 */
public class NumberFiniteStateMachine extends FiniteStateMachine<StringBuilder> {
    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(NumberFiniteStateMachine.class);
    private final MinusState minus = new MinusState();
    private final DigitState integer = new DigitState();
    private final DotState dot = new DotState();
    private final DigitState decimal = new DigitState();

    public NumberFiniteStateMachine() throws InvalidSymbolException {
        logger.info("Create Number FSM");

        minus.addTransition(integer);
        integer.addTransition(integer, dot);
        dot.addTransition(decimal);
        decimal.addTransition(decimal);

    }


    @Override
    public List<State<StringBuilder>> getStartStates() {
        return Arrays.asList(minus, integer);
    }

    @Override
    public List<State<StringBuilder>> getFinishStates() {
        return Arrays.asList(integer, decimal);
    }
}
