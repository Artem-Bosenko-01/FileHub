package com.teamdev.calculator.compiler.function;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.functions.FunctionScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is name state, that extends {@link State basic state} opportunity.
 * It used like state in {@link FunctionFiniteStateMachine function FSM}
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class NameState extends State<FunctionScope> {
    private final Logger logger = LoggerFactory.getLogger(NameState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, FunctionScope output) {
        logger.info("Start transition for Name state in function");
        NameFiniteStateMachine machine = new NameFiniteStateMachine();
        StringBuilder builder = new StringBuilder(10);
        if(machine.execute(characterStream, builder)){
            output.addName(builder.toString());
            logger.info("transition NameState successful");
            return true;
        }
        return false;
    }

}
