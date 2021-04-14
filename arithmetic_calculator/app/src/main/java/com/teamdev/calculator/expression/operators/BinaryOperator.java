package com.teamdev.calculator.expression.operators;

public interface BinaryOperator {
    int getPriority();
    double apply(double leftArgument, double rightArgument);
}
