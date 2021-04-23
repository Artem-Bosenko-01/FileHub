package com.teamdev.calculator.runtime;

import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

public class OperandCommand implements Command<ShuntingYardStack>{
    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(OperatorCommand.class);
    private final double operand;

    public OperandCommand(double operand) {
        this.operand = operand;
    }

    @Override
    public void execute(ShuntingYardStack stack) {
        logger.info("Start execute command for Operand");
        stack.pushOperand(operand);
        logger.info("Finish execute command for Operand");
    }
}
