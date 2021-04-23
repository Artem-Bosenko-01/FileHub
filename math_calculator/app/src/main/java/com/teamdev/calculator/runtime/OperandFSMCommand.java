package com.teamdev.calculator.runtime;

import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

public class OperandFSMCommand implements Command<ShuntingYardStack>{
    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(OperandFSMCommand.class);
    private final ShuntingYardStack mainStack;

    public OperandFSMCommand(ShuntingYardStack mainStack) {
        this.mainStack = mainStack;
    }

    @Override
    public void execute(ShuntingYardStack stack) {
        logger.info("Start execute command for Operand FSM");
        stack.pushOperand(mainStack.calculate());
        logger.info("End execute command for Operand FSM");
    }
}
