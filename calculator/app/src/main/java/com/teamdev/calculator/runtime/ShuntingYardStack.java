package com.teamdev.calculator.runtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

/**
 * This is <a href="https://en.wikipedia.org/wiki/Stack_(abstract_data_type)">stack</a>, that implement <a href="https://en.wikipedia.org/wiki/Shunting-yard_algorithm">Shunting-yard algorithm</a>
 * */
public class ShuntingYardStack implements Cloneable{

    private final Logger logger = LoggerFactory.getLogger(ShuntingYardStack.class);
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
            Double rightOperand = operands.pop();
            BinaryOperator operator = operators.pop();
            Double leftOperand = operands.pop();

            operands.push(operator.apply(leftOperand,rightOperand));
        }
        logger.info("End calculate, value = " + peekOperand());
        return operands.pop();
    }
}
