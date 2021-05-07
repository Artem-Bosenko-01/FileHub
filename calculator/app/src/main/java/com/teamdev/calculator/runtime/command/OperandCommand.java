package com.teamdev.calculator.runtime.command;

import com.teamdev.calculator.compiler.operand.OperandCompiler;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.holder.ValueHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is {@link Command command}, that generated after compiling by
 * {@link OperandCompiler operand compiler}
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class OperandCommand implements Command<ShuntingYardStack>{
    private final Logger logger = LoggerFactory.getLogger(OperatorCommand.class);
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
