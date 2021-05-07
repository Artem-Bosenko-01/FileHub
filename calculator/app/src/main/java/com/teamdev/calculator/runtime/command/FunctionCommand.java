package com.teamdev.calculator.runtime.command;

import com.teamdev.calculator.runtime.functions.Function;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.holder.ValueHolder;
import com.teamdev.calculator.runtime.holder.value.DoubleValueHolder;

import java.util.List;
import java.util.Optional;

/**
 * This command calculate result of the specific {@link Command function}
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
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
