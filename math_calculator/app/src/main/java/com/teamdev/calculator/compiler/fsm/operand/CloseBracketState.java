package com.teamdev.calculator.compiler.fsm.operand;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 *This defines close bracket symbol in {@link com.teamdev.calculator.compiler.fsm.ExpressionFiniteStateMachine expression}
 * or {@link com.teamdev.calculator.compiler.fsm.function.FunctionFiniteStateMachine function}.
 * Type of {@link com.teamdev.calculator.compiler.fsm.FiniteStateMachine FSM} defines by parameter {@link T T}
 * */
public class CloseBracketState<T> extends State<T> {
    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(CloseBracketState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, T builder) {
        if(String.valueOf(characterStream.getCurrentSymbol()).equals(")")){
            logger.info("Try transition for close bracket state");
            characterStream.increasePointer();
            logger.info("Transition successful");
            return true;
        }
        return false;
    }
}
