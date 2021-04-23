package com.teamdev.calculator.compiler.fsm.number;


import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Arrays;

public class NumberFiniteStateMachine extends FiniteStateMachine<StringBuilder> {
    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(NumberFiniteStateMachine.class);
    public NumberFiniteStateMachine() {
        logger.info("Create Number FSM");
        MinusState minus = new MinusState();
        DigitState integer = new DigitState();
        DotState dot = new DotState();
        DigitState decimal = new DigitState();

        minus.addTransition(integer);
        integer.addTransition(integer, dot);
        dot.addTransition(decimal);
        decimal.addTransition(decimal);

        setStartStates(Arrays.asList(minus, integer));
        setFinishStates(Arrays.asList(integer, decimal));

    }



}
