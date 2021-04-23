package com.teamdev.calculator.runtime.impl;

import com.teamdev.calculator.runtime.BinaryOperator;

/**
 * Note: this class has a natural ordering that is inconsistent with equals.
 */
public class Plus implements BinaryOperator {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public double apply(double leftArgument, double rightArgument) {
        return leftArgument + rightArgument;
    }

    @Override
    public int compareTo(BinaryOperator o) {

        return Integer.compare(this.getPriority(), o.getPriority());
    }
}
