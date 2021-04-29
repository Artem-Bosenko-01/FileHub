package com.teamdev.calculator.runtime;

import java.util.List;

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
         stack.pushOperand(function.apply(arguments));
    }
}
