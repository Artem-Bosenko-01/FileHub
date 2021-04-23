package com.teamdev.calculator.compiler.fsm.function;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.FunctionScope;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

public class OpenBracketFunctionState extends State<FunctionScope> {
    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(OpenBracketFunctionState.class);

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, FunctionScope builder) {
        logger.info("Start transition for Open bracket in function");
        if (characterStream.getCurrentSymbol() == '(') {
            characterStream.increasePointer();
            logger.info("transition successful");
            return true;
        }
        return false;
    }
}
