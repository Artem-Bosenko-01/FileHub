package com.teamdev.calculator.compiler.fsm.function;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.FunctionScope;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 * This is name state, that extends {@link State basic state} opportunity.
 * It used like state in {@link FunctionFiniteStateMachine function FSM}
 * */
public class NameState extends State<FunctionScope> {
    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(NameState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, FunctionScope output) {
        NameFiniteStateMachine machine = new NameFiniteStateMachine();
        StringBuilder builder = new StringBuilder();
        logger.info("Start transition for Name state in function");
        try {
            if(machine.execute(characterStream, builder)){
                output.addName(builder.toString());
                logger.info("transition successful");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
