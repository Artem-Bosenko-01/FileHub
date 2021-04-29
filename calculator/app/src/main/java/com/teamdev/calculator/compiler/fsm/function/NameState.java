package com.teamdev.calculator.compiler.fsm.function;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.runtime.FunctionScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 * This is name state, that extends {@link State basic state} opportunity.
 * It used like state in {@link FunctionFiniteStateMachine function FSM}
 * */
public class NameState extends State<FunctionScope> {
    private final Logger logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(NameState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, FunctionScope output) {
        logger.info("Start transition for Name state in function");
        try {
            NameFiniteStateMachine machine = new NameFiniteStateMachine();
            StringBuilder builder = new StringBuilder();
            if(machine.execute(characterStream, builder)){
                output.addName(builder.toString());
                logger.info("transition successful");
                return true;
            }
        } catch (InvalidSymbolException | NotExistPairBracketException e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public static class Builder{
        private final NameState newState;

        public Builder(){
            newState = new NameState();
        }
        public Builder setTransition(State<FunctionScope>... transitions){
            newState.addTransition(transitions);
            return this;
        }
        public Builder isLoop(boolean result){
            if (result) newState.addTransition(newState);
            return this;
        }
        public NameState build(){
            return newState;
        }
    }
}
