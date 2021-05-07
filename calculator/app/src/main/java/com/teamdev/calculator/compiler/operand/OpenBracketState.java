package com.teamdev.calculator.compiler.operand;

import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.doubleexpression.ExpressionFiniteStateMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *This defines open bracket symbol in {@link ExpressionFiniteStateMachine expression}
 * or {@link com.teamdev.calculator.compiler.function.FunctionFiniteStateMachine function}.
 * Type of {@link FiniteStateMachine FSM} defines by parameter {@link T T}
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class OpenBracketState<T> extends State<T> {
    private final Logger logger = LoggerFactory.getLogger(OpenBracketState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, T builder) {
        logger.info("Try transition for open bracket state");
        if(String.valueOf(characterStream.getCurrentSymbol()).equals("(")){
            characterStream.increasePointer();
            logger.info("Transition OpenBracketState successful");
            return true;
        }
        return false;
    }
}
