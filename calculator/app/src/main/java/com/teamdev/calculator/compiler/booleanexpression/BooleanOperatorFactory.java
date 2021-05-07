package com.teamdev.calculator.compiler.booleanexpression;

import com.teamdev.calculator.runtime.operators.Operator;
import com.teamdev.calculator.runtime.operators.Less;
import com.teamdev.calculator.runtime.operators.LessOrEquals;
import com.teamdev.calculator.runtime.operators.More;
import com.teamdev.calculator.runtime.operators.MoreOrEquals;
import com.teamdev.calculator.runtime.operators.Equals;
import com.teamdev.calculator.runtime.operators.NotEquals;

final class BooleanOperatorFactory {

    private BooleanOperatorFactory() {
    }

    public static Operator getOperator(String str) {
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
