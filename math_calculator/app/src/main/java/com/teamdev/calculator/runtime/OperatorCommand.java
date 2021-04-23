package com.teamdev.calculator.runtime;

import com.teamdev.calculator.compiler.fsm.expression.ChooseOperator;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

public class OperatorCommand implements Command<ShuntingYardStack>{
    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(OperatorCommand.class);
    private final String symbol;

    public OperatorCommand(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public void execute(ShuntingYardStack stack) {
        logger.info("Start execute command for Operator");
        stack.pushOperator(ChooseOperator.getOperator(symbol));
        logger.info("End execute command for Operator");
    }
}
