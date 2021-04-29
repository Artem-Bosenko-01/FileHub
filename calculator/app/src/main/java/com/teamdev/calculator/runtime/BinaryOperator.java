package com.teamdev.calculator.runtime;

/**
 * This is basic API for binary operators, that are used in
 * {@link com.teamdev.calculator.compiler.fsm.OperatorState operator state}
 * */
public interface BinaryOperator extends Comparable<BinaryOperator>{
    int getPriority();
    double apply(double leftArgument, double rightArgument);
}
