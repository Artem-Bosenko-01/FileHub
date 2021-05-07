package com.teamdev.calculator.compiler.number;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *This is {@link State state}, that define is input symbol equals digit symbol.
 * It used like one of states in {@link NumberFiniteStateMachine number FSM}
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class DigitState extends State<StringBuilder> {
    private final Logger logger = LoggerFactory.getLogger(State.class);

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        logger.info("Try transition for digit state");
        if (Character.isDigit(characterStream.getCurrentSymbol())) {
            builder.append(characterStream.getCurrentSymbol());
            characterStream.increasePointer();
            logger.info("Transition DigitState successful");
            return true;
        }
        return false;
    }
}
