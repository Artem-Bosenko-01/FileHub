package com.teamdev.calculator.fsm.expression;

import com.teamdev.calculator.runtime.operators.BinaryOperator;
import com.teamdev.calculator.runtime.operators.impl.*;

public final class ChooseOperator {
    public static BinaryOperator getOperator(String symbol){
        switch (symbol){
            case "+": return new Plus();
            case "-": return new Subtract();
            case "*": return new Multiply();
            case "/": return new Devide();
            case "^": return new Degree();
        }
        throw new RuntimeException("invalid symbol. not operator");
    }
}
