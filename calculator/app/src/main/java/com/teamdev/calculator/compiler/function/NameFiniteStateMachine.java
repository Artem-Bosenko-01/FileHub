package com.teamdev.calculator.compiler.function;

import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.number.DigitState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This is name FSM, that extends {@link FiniteStateMachine basic FSM} possibility.
 * It used to define name of function in {@link com.teamdev.calculator.compiler.InputCharacterStream input stream}
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class NameFiniteStateMachine extends FiniteStateMachine<StringBuilder> {

    private final LetterState letterState = new LetterState();
    private final DigitState digitState = new DigitState();

    public NameFiniteStateMachine(){

        Logger logger = LoggerFactory.getLogger(NameFiniteStateMachine.class);
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
