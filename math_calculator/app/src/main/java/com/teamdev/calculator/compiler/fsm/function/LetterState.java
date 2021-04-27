package com.teamdev.calculator.compiler.fsm.function;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 * This is letter state, that extends {@link State basic state}.
 * It used like state in {@link NameFiniteStateMachine name FSM}
 * */
public class LetterState extends State<StringBuilder> {
    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(LetterState.class);

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        logger.info("Start transition for letter state in name");
        if (String.valueOf(characterStream.getCurrentSymbol()).matches("[a-zA-Z]")) {
            builder.append(characterStream.getCurrentSymbol());
            characterStream.increasePointer();
            logger.info("transition successful");
            return true;
        }
        return false;
    }
}
