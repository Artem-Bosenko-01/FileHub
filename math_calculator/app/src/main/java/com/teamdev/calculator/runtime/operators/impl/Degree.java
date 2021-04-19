package com.teamdev.calculator.runtime.operators.impl;

import com.teamdev.calculator.runtime.operators.BinaryOperator;

public class Degree implements BinaryOperator {
    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public double apply(double leftArgument, double rightArgument) {
        return Math.pow(leftArgument,rightArgument);
    }

    @Override
    public int compareTo(BinaryOperator o) {

        return Integer.compare(this.getPriority(), o.getPriority());
    }
}
