package com.teamdev.calculator.runtime;

import com.teamdev.calculator.runtime.holder.ValueHolder;
import com.teamdev.calculator.runtime.holder.doubletype.DoubleValueHolder;

import java.util.List;
import java.util.Optional;

/**
 * This command calculate result of the specific {@link Command function}
 * */
public class FunctionCommand implements Command<ShuntingYardStack>{
    private final Function function;
    private final List<ValueHolder<?>> arguments;

    public FunctionCommand(Function function, List<ValueHolder<?>> arguments) {
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public void execute(ShuntingYardStack stack) {
        Optional<Double> operand = function.apply(arguments);
        operand.ifPresent(aDouble -> stack.pushOperand(new DoubleValueHolder(aDouble)));
    }
}
