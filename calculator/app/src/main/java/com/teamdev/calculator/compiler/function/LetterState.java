package com.teamdev.calculator.compiler.function;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * This is letter state, that extends {@link State basic state}.
 * It used like state in {@link NameFiniteStateMachine name FSM}
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class LetterState extends State<StringBuilder> {
    private final Logger logger = LoggerFactory.getLogger(LetterState.class);
    private final Pattern pattern = Pattern.compile("[a-zA-Z]");
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        logger.info("Start transition for letter state in name");
        if(pattern.matcher(String.valueOf(characterStream.getCurrentSymbol())).matches()) {
            builder.append(characterStream.getCurrentSymbol());
            characterStream.increasePointer();
            logger.info("transition LetterState successful");
            return true;
        }
        return false;
    }
}
