package com.teamdev.calculator.compiler.fsm.function;

import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.compiler.fsm.number.DigitState;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This is name FSM, that extends {@link FiniteStateMachine basic FSM} possibility.
 * It used to define name of function in {@link com.teamdev.calculator.compiler.InputCharacterStream input stream}
 * */
public class NameFiniteStateMachine extends FiniteStateMachine<StringBuilder> {

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(NameFiniteStateMachine.class);
    private final LetterState letterState = new LetterState();
    private final DigitState digitState = new DigitState();

    public NameFiniteStateMachine(){

        logger.info("Start Name FSM");


        letterState.addTransition(letterState, digitState);
        digitState.addTransition(digitState);

    }

    @Override
    public List<State<StringBuilder>> getStartStates() {
        return Collections.singletonList(letterState);
    }

    @Override
    public List<State<StringBuilder>> getFinishStates() {
        return Arrays.asList(letterState, digitState);
    }
}
