package com.teamdev.calculator.runtime;

public interface BinaryOperator extends Comparable<BinaryOperator>{
    int getPriority();
    double apply(double leftArgument, double rightArgument);
}
