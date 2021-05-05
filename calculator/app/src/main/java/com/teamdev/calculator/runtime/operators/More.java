package com.teamdev.calculator.runtime.operators;

import com.teamdev.calculator.runtime.BooleanBinaryOperator;

public class More implements BooleanBinaryOperator {

    @Override
    public Boolean apply(double leftArgument, double rightArgument) {
        return leftArgument>rightArgument;
    }
}
