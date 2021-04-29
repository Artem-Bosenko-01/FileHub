package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.List;

public class LogFunction extends Function {
    public LogFunction() {
        super("log");
    }

    @Override
    public double apply(List<Double> arguments) {
        return Math.log(arguments.get(0));
    }
}
