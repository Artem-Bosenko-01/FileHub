package com.teamdev.calculator.runtime.operators;

import com.teamdev.calculator.compiler.exception.InvalidBinaryOperatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *This utility-class is used to detects {@link Operator binary operator} for an incoming character.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public final class ChooseOperator {

    private static final Logger logger = LoggerFactory.getLogger(ChooseOperator.class);
    private ChooseOperator() {
    }

    public static Operator getOperator(String symbol) throws InvalidBinaryOperatorException {
        logger.info("Choose operator for " + symbol);
        switch (symbol){
            case "+": return new Plus();
            case "-": return new Subtract();
            case "*": return new Multiply();
            case "/": return new Divide();
            case "^": return new Degree();
            default: throw new InvalidBinaryOperatorException(symbol);
        }
    }
}
