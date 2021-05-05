package com.teamdev.calculator.runtime;

import java.util.ArrayList;
import java.util.List;

public class BooleanScope {

    private final List<Double> operands = new ArrayList<>();
    private BooleanBinaryOperator operator;


    public void addOperand(double operand) {
        operands.add(operand);
    }

    public void setOperator(BooleanBinaryOperator operator) {
        this.operator = operator;
    }

    public List<Double> getOperands() {
        return operands;
    }

    public BooleanBinaryOperator getOperator() {
        return operator;
    }
}
