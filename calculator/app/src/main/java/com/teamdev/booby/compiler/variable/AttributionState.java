package com.teamdev.booby.compiler.variable;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is one of states for {@link InitVariableFiniteStateMachine init variable FSM}, that
 * detects attribution symbol in {@link InputCharacterStream input stream}.
 */
public class AttributionState extends State<StringBuilder> {

    private final Logger logger = LoggerFactory.getLogger(AttributionState.class);

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        logger.info("Try transition for attribution state");
        if(characterStream.getCurrentSymbol() == '='){
            builder.append(characterStream.getCurrentSymbol());
            characterStream.increasePointer();
            logger.info("Transition AttributionState successful");
            return true;
        }
        return false;
    }
}
