package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.List;

public class SinFunction extends Function {
    public SinFunction() {
        super("sin");
    }

    @Override
    public double apply(List<Double> arguments) {
        return Math.sin(arguments.get(0));
    }
}
