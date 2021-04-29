package com.teamdev.calculator.compiler.fsm.operand;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 *This defines open bracket symbol in {@link com.teamdev.calculator.compiler.fsm.ExpressionFiniteStateMachine expression}
 * or {@link com.teamdev.calculator.compiler.fsm.function.FunctionFiniteStateMachine function}.
 * Type of {@link com.teamdev.calculator.compiler.fsm.FiniteStateMachine FSM} defines by parameter {@link T T}
 * */
public class OpenBracketState<T> extends State<T> {
    private final Logger logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(OpenBracketState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, T builder) {
        logger.info("Try transition for open bracket state");
        if(String.valueOf(characterStream.getCurrentSymbol()).equals("(")){
            characterStream.increasePointer();
            logger.info("Transition successful");
            return true;
        }
        return false;
    }

    public static class Builder<T>{
        private final OpenBracketState<T> newState;

        public Builder(){
            newState = new OpenBracketState<>();
        }
        public Builder<T> setTransition(State<T>... transitions){
            newState.addTransition(transitions);
            return this;
        }
        public Builder<T> isLoop(boolean result){
            if (result) newState.addTransition(newState);
            return this;
        }
        public OpenBracketState<T> build(){
            return newState;
        }
    }
}
