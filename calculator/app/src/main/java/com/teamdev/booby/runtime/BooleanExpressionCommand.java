package com.teamdev.booby.runtime;

import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.command.OperandCommand;
import com.teamdev.calculator.runtime.holder.value.DoubleValueHolder;
import com.teamdev.calculator.runtime.operators.Operator;

public class BooleanExpressionCommand implements Command<ShuntingYardStack> {

    private final Double leftOperand;
    private final Operator operator;
    private final Double rightOperand;

    public BooleanExpressionCommand(Double leftOperand, Operator operator, Double rightOperand) {
        this.leftOperand = leftOperand;
        this.operator = operator;
        this.rightOperand = rightOperand;
    }

    @Override
    public void execute(ShuntingYardStack stack) {
        stack.pushOperand(operator.apply(new DoubleValueHolder(leftOperand), new DoubleValueHolder(rightOperand)));
    }
}
