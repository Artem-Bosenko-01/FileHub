package com.teamdev.calculator.compiler.fsm;

import com.teamdev.calculator.compiler.fsm.exception.InvalidBinaryOperatorException;
import com.teamdev.calculator.runtime.BinaryOperator;
import com.teamdev.calculator.runtime.double_operators.Degree;
import com.teamdev.calculator.runtime.double_operators.Plus;
import com.teamdev.calculator.runtime.double_operators.Subtract;
import com.teamdev.calculator.runtime.double_operators.Multiply;
import com.teamdev.calculator.runtime.double_operators.Divide;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 *This utility-class is used to detects {@link BinaryOperator binary operator} for an incoming character
 * */
public final class ChooseOperator {

    private static final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(ChooseOperator.class);
    private ChooseOperator() {
    }

    public static BinaryOperator getOperator(String symbol) throws InvalidBinaryOperatorException {
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
