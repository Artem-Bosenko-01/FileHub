package com.teamdev.calculator.compiler.number;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *This is {@link State state}, that define is input symbol equals minus symbol.
 * It used like one of states in {@link NumberFiniteStateMachine number FSM}
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class MinusState extends State<StringBuilder> {

    private final Logger logger = LoggerFactory.getLogger(MinusState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        logger.info("Try transition for minus state");
        if (characterStream.getCurrentSymbol() =='-') {
            builder.append(characterStream.getCurrentSymbol());
            characterStream.increasePointer();
            logger.info("Transition MinusState successful");
            return true;
        }
        return false;
    }
}
