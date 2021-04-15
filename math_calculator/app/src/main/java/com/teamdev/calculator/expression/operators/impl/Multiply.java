package com.teamdev.calculator.expression.operators.impl;

import com.teamdev.calculator.expression.operators.BinaryOperator;

public class Multiply implements BinaryOperator {
    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public double apply(double leftArgument, double rightArgument) {
        return leftArgument*rightArgument;
    }

    @Override
    public int compareTo(Object o) {
        BinaryOperator operator = (BinaryOperator)o;

        return Integer.compare(this.getPriority(), operator.getPriority());
    }
}
