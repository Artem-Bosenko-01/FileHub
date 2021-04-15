package com.teamdev.calculator.expression.operators;

public interface BinaryOperator extends Comparable{
    int getPriority();
    double apply(double leftArgument, double rightArgument);
}
