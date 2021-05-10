package com.teamdev.calculator.runtime.command;

import com.teamdev.calculator.runtime.operators.ChooseOperator;
import com.teamdev.calculator.compiler.doubleexpression.OperatorState;
import com.teamdev.calculator.compiler.exception.InvalidBinaryOperatorException;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is {@link Command command}, that generated after successful path transition from
 * {@link OperatorState operator state}.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class OperatorCommand implements Command<ShuntingYardStack>{
    private final Logger logger = LoggerFactory.getLogger(OperatorCommand.class);
    private final String symbol;

    public OperatorCommand(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public void execute(ShuntingYardStack stack) {
        logger.info("Start execute command for Operator");
        try {
            stack.pushOperator(ChooseOperator.getOperator(symbol));
        } catch (InvalidBinaryOperatorException invalidBinaryOperator) {
            logger.error(invalidBinaryOperator.getLocalizedMessage());
        }
        logger.info("End execute command for Operator");
    }
}
