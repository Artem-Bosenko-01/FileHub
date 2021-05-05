package com.teamdev.calculator.compiler.fsm.booleanexpression;

import com.teamdev.calculator.runtime.BooleanBinaryOperator;
import com.teamdev.calculator.runtime.operators.*;

public final class BooleanOperatorFactory {

    public static BooleanBinaryOperator getOperator(String str) {
        switch (str) {
            case "<":
                return new Less();
            case "<=":
                return new LessOrEquals();
            case ">":
                return new More();
            case ">=":
                return new MoreOrEquals();
            case "==":
                return new Equals();
            case "!=":
                return new NotEquals();
            default: throw new RuntimeException();
        }
    }
}
