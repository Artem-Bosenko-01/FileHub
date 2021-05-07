package com.teamdev.calculator.compiler.number;


import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *This is {@link State state}, that define is input symbol equals dot symbol.
 * It used like one of states in {@link NumberFiniteStateMachine number FSM}
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class DotState extends State<StringBuilder> {

    private final Logger logger = LoggerFactory.getLogger(DotState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        logger.info("Try transition for dot state");
        if (characterStream.getCurrentSymbol() == '.') {
            builder.append(characterStream.getCurrentSymbol());
            characterStream.increasePointer();
            logger.info("Transition DotState successful");
            return true;
        }
        return false;
    }


}
