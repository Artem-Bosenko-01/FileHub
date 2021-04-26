package com.teamdev.calculator.compiler.fsm;

import com.teamdev.calculator.runtime.BinaryOperator;
import com.teamdev.calculator.runtime.impl.Degree;
import com.teamdev.calculator.runtime.impl.Plus;
import com.teamdev.calculator.runtime.impl.Subtract;
import com.teamdev.calculator.runtime.impl.Multiply;
import com.teamdev.calculator.runtime.impl.Divide;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;


public final class ChooseOperator {

    private static final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(ChooseOperator.class);
    private ChooseOperator() {
    }

    public static BinaryOperator getOperator(String symbol){
        logger.info("Choose operator for " + symbol);
        switch (symbol){
            case "+": return new Plus();
            case "-": return new Subtract();
            case "*": return new Multiply();
            case "/": return new Divide();
            case "^": return new Degree();
            default: throw new RuntimeException("invalid symbol. not operator");
        }
    }
}
