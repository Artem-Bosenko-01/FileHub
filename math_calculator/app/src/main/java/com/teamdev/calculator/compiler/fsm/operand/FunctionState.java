package com.teamdev.calculator.compiler.fsm.operand;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.impl.FunctionCompiler;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Optional;

public class FunctionState extends State<ShuntingYardStack> {
    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(FunctionState.class);

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        logger.info("Try transition for function state");
        Optional<Command<ShuntingYardStack>> command = new FunctionCompiler().compile(characterStream);


        if(command.isPresent()){
            command.get().execute(builder);
            return true;
        }
        logger.info("Transition successful");
        return false;
    }
}
