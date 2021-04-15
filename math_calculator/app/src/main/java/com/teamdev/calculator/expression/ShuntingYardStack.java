package com.teamdev.calculator.expression;

import com.teamdev.calculator.expression.operators.BinaryOperator;

import java.util.Stack;

public class ShuntingYardStack {
    private final Stack<BinaryOperator> operators = new Stack();
    private final Stack<Double> operands = new Stack();

    public void pushOperator(BinaryOperator binaryOperator){
        operators.push(binaryOperator);
    }
    public void pushOperand(Double operand){
        operands.push(operand);
    }

    public double calculate(){

        while (!operators.isEmpty()){

            operands.push(operators.pop().apply(operands.pop(),operands.pop()));
        }

        return operands.pop();
    }
}
