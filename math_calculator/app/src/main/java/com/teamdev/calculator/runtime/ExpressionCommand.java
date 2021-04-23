package com.teamdev.calculator.runtime;

import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

public class ExpressionCommand implements Command<ShuntingYardStack> {

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(ExpressionCommand.class);
    private final ShuntingYardStack mainStack;

    public ExpressionCommand(ShuntingYardStack mainStack) {
        this.mainStack = mainStack;
    }

    @Override
    public void execute(ShuntingYardStack stack) {
        logger.info("Start execute command for Expression");
        stack.clone(mainStack);
        logger.info("Finish execute command for Expression");
    }
}
