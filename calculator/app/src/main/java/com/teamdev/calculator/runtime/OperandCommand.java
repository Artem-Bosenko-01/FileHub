package com.teamdev.calculator.runtime;

import com.teamdev.calculator.runtime.holder.ValueHolder;
import com.teamdev.calculator.runtime.holder.doubletype.DoubleValueHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 * This is {@link Command command}, that generated after compiling by
 * {@link com.teamdev.calculator.compiler.impl.OperandCompiler operand compiler}
 * */
public class OperandCommand implements Command<ShuntingYardStack>{
    private final Logger logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(OperatorCommand.class);
    private final ValueHolder<?> operand;

    public OperandCommand(ValueHolder<?> operand) {
        this.operand = operand;
    }

    @Override
    public void execute(ShuntingYardStack stack) {
        logger.info("Start execute command for Operand");
        stack.pushOperand(operand);
        logger.info("Finish execute command for Operand");
    }
}
