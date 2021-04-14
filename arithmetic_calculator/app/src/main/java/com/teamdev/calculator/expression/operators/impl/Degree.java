package com.teamdev.calculator.expression.operators.impl;

import com.teamdev.calculator.expression.operators.BinaryOperator;

public class Degree implements BinaryOperator {
    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public double apply(double leftArgument, double rightArgument) {
        return Math.pow(leftArgument,rightArgument);
    }
}
