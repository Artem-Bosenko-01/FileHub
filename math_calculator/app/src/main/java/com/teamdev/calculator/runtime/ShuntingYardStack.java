package com.teamdev.calculator.runtime;

import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class ShuntingYardStack implements Cloneable{

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(ShuntingYardStack.class);
    private final Deque<BinaryOperator> operators = new ArrayDeque<>();
    private final Deque<Double> operands = new ArrayDeque<>();

    public void pushOperator(BinaryOperator binaryOperator){
        logger.info("Start push operator" + binaryOperator.toString() + "with priority = " + binaryOperator.getPriority());
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

    public void clone(ShuntingYardStack stack){
        logger.info("Start clone function with " + stack.toString());
        operands.addAll(stack.operands);
        operators.addAll(stack.operators);
    }

    public Optional<Double> peekOperand(){
        return Optional.ofNullable(operands.peek());
    }

    public void pushOperand(Double operand){
        logger.info("Start push operand = " + operand);
        operands.push(operand);
    }

    public double calculate(){
        logger.info("Start calculate result");
        while (!operators.isEmpty()){
            double rightOperand = operands.pop();
            BinaryOperator operator = operators.pop();
            double leftOperand = operands.pop();

            operands.push(operator.apply(leftOperand,rightOperand));
        }
        logger.info("End calculate, value = " + peekOperand());
        return operands.pop();
    }
}
