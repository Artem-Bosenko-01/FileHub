package com.teamdev.calculator.expression.operators.impl;

import com.teamdev.calculator.expression.operators.BinaryOperator;

public class Subtract implements BinaryOperator {
    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public double apply(double leftArgument, double rightArgument) {
        return leftArgument - rightArgument;
    }
}
