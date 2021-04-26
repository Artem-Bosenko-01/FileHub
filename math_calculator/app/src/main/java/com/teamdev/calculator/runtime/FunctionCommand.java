package com.teamdev.calculator.runtime;

import java.util.List;

public class FunctionCommand implements Command<ShuntingYardStack>{
    private final Function function;
    private final List<Double> arguments;

    public FunctionCommand(Function function, List<Double> arguments) {
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public void execute(ShuntingYardStack stack) {
        if(!function.getName().equals("pi")) stack.pushOperand(function.apply(arguments));
    }
}
