package com.teamdev.calculator.runtime;

import com.teamdev.calculator.runtime.operators.BinaryOperator;

import java.util.Stack;

public class ShuntingYardStack {
    private final Stack<BinaryOperator> operators = new Stack<BinaryOperator>();
    private final Stack<Double> operands = new Stack<Double>();

    public void pushOperator(BinaryOperator binaryOperator){
        if (operators.size() > 0){
            if(binaryOperator.compareTo(operators.peek()) > 0)
            {
                operators.push(binaryOperator);
            }
            else {
                double rightOperand = operands.pop();
                BinaryOperator operator = operators.pop();
                double leftOperand = operands.pop();

               operands.push(operator.apply(leftOperand,rightOperand));
               pushOperator(binaryOperator);
            }
        }else operators.push(binaryOperator);
    }

    public void pushOperand(Double operand){
        operands.push(operand);
    }

    public double calculate(){

        while (!operators.isEmpty()){
            double rightOperand = operands.pop();
            BinaryOperator operator = operators.pop();
            double leftOperand = operands.pop();

            operands.push(operator.apply(leftOperand,rightOperand));
        }

        return operands.pop();
    }
}
