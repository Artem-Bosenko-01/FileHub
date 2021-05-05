package com.teamdev.calculator.runtime;

import java.util.List;
import java.util.Optional;

/**
 * This command calculate result of the specific {@link Command function}
 * */
public class FunctionCommand implements Command<ShuntingYardStack>{
    private final Function function;
    private final List<Double> arguments;

    public FunctionCommand(Function function, List<Double> arguments) {
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public void execute(ShuntingYardStack stack) {
        Optional<Double> operand = function.apply(arguments);
        operand.ifPresent(stack::pushOperand);
    }
}
