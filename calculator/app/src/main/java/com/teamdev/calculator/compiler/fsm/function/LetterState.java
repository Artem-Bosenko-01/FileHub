package com.teamdev.calculator.compiler.fsm.function;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.regex.Pattern;

/**
 * This is letter state, that extends {@link State basic state}.
 * It used like state in {@link NameFiniteStateMachine name FSM}
 * */
public class LetterState extends State<StringBuilder> {
    private final Logger logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(LetterState.class);

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

    public static class Builder{
        private final LetterState newState;

        public Builder(){
            newState = new LetterState();
        }
        public Builder setTransition(State<StringBuilder>... transitions){
            newState.addTransition(transitions);
            return this;
        }
        public Builder isLoop(boolean result){
            if (result) newState.addTransition(newState);
            return this;
        }
        public LetterState build(){
            return newState;
        }
    }
}
