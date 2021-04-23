package com.teamdev.calculator.compiler.fsm.number;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

public class MinusState extends State<StringBuilder> {

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(MinusState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        logger.info("Try transition for minus state");
        if (characterStream.getCurrentSymbol() =='-') {
            builder.append(characterStream.getCurrentSymbol());
            characterStream.increasePointer();
            logger.info("Transition successful");
            return true;
        }
        return false;
    }
}
