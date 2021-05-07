package com.teamdev.calculator.runtime;

import com.teamdev.calculator.runtime.holder.ValueHolder;
import com.teamdev.calculator.runtime.operators.Operator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

/**
 * This is <a href="https://en.wikipedia.org/wiki/Stack_(abstract_data_type)">stack</a>, that implement <a href="https://en.wikipedia.org/wiki/Shunting-yard_algorithm">Shunting-yard algorithm</a>
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyDependents"})
public class ShuntingYardStack implements Cloneable{

    private final Logger logger = LoggerFactory.getLogger(ShuntingYardStack.class);
    private final Deque<Operator> operators = new ArrayDeque<>();
    private final Deque<ValueHolder<?>> operands = new ArrayDeque<>();

    public void pushOperator(Operator binaryOperator){
        logger.info("Start push operator" + binaryOperator.toString() + "with priority = " + binaryOperator.getPriority());
        if (operators.size() > 0){
            if(binaryOperator.compareTo(operators.peek()) > 0)
            {
                operators.push(binaryOperator);
            }
            else {
                ValueHolder<?> rightOperand = operands.pop();
                Operator operator = operators.pop();
                ValueHolder<?> leftOperand = operands.pop();

               operands.push(operator.apply(leftOperand,rightOperand));
               pushOperator(binaryOperator);
            }
        }else operators.push(binaryOperator);
    }

    public Optional<ValueHolder<?>> peekOperand(){
        return Optional.ofNullable(operands.peek());
    }

    public void pushOperand(ValueHolder<?> operand){
        logger.info("Start push operand = " + operand);
        operands.push(operand);
    }

    public ValueHolder<?> calculate(){
        logger.info("Start calculate result");
        while (!operators.isEmpty()){
            ValueHolder<?> rightOperand = operands.pop();
            Operator operator = operators.pop();
            ValueHolder<?> leftOperand = operands.pop();

            operands.push(operator.apply(leftOperand,rightOperand));
        }
        logger.info("End calculate, value = " + peekOperand());
        return operands.pop();
    }
}
