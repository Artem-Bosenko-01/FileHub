package com.teamdev.calculator.compiler.fsm.function;

import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.number.DigitState;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Arrays;
import java.util.Collections;

public class NameFiniteStateMachine extends FiniteStateMachine<StringBuilder> {

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(NameFiniteStateMachine.class);

    public NameFiniteStateMachine(){

        logger.info("Start Name FSM");
        LetterState letterState = new LetterState();
        DigitState digitState = new DigitState();
        letterState.addTransition(letterState, digitState);
        digitState.addTransition(digitState);

        setStartStates(Collections.singletonList(letterState));
        setFinishStates(Arrays.asList(letterState, digitState));
    }
}
