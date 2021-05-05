package com.teamdev.calculator.runtime;

import java.util.ArrayList;
import java.util.List;

public class BooleanScope {

    private final List<Double> operands = new ArrayList<>();
    private Operator operator;


    public void addOperand(Double operand) {
        operands.add(operand);
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public List<Double> getOperands() {
        return operands;
    }

    public Operator getOperator() {
        return operator;
    }
}
