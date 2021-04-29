package com.teamdev.calculator.compiler.fsm.function;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.FunctionScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 * This is comma state, that extends {@link State basic state}
 * This class are used to define comma symbol state in {@link FunctionFiniteStateMachine function FSM}
 * */
public class CommaState extends State<FunctionScope> {
    private final Logger logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(CommaState.class);

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, FunctionScope builder) {
        logger.info("Start transition for ',' state in function");
        if (characterStream.getCurrentSymbol() == ',') {
            characterStream.increasePointer();
            logger.info("transition successful");
            return true;
        }
        return false;
    }

    public static class Builder{
        private final CommaState newState;

        public Builder(){
            newState = new CommaState();
        }
        public Builder setTransition(State<FunctionScope>... transitions){
            newState.addTransition(transitions);
            return this;
        }
        public Builder isLoop(boolean result){
            if (result) newState.addTransition(newState);
            return this;
        }
        public CommaState build(){
            return newState;
        }
    }
}
