package com.teamdev.calculator.compiler.function;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.functions.FunctionScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is comma state, that extends {@link State basic state}
 * This class are used to define comma symbol state in {@link FunctionFiniteStateMachine function FSM}.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class CommaState extends State<FunctionScope> {
    private final Logger logger = LoggerFactory.getLogger(CommaState.class);

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, FunctionScope builder) {
        logger.info("Start transition for ',' state in function");
        if (characterStream.getCurrentSymbol() == ',') {
            characterStream.increasePointer();
            logger.info("transition CommaState successful");
            return true;
        }
        return false;
    }

}
