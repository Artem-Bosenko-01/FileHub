package com.teamdev.calculator.compiler.fsm.operand;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

public class CloseBracketState extends State<ShuntingYardStack> {
    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(CloseBracketState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        if(String.valueOf(characterStream.getCurrentSymbol()).equals(")")){
            logger.info("Try transition for close bracket state");
            characterStream.increasePointer();
            logger.info("Transition successful");
            return true;
        }
        return false;
    }
}
