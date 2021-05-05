package com.teamdev.calculator.runtime.operators;

import com.teamdev.calculator.runtime.BinaryOperator;
import com.teamdev.calculator.runtime.Operator;

/**
 * Note: this class has a natural ordering that is inconsistent with equals.
 */
public class Degree implements BinaryOperator {
    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public Double apply(double leftArgument, double rightArgument) {
        return StrictMath.pow(leftArgument,rightArgument);
    }


    @Override
    public int compareTo(Operator<Double> o) {
         return Integer.compare(this.getPriority(), o.getPriority());
    }
}
