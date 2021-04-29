package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.List;

public class LgFunction extends Function {
    public LgFunction() {
        super("log10");
    }

    @Override
    public double apply(List<Double> arguments) {
        return Math.log10(arguments.get(0));
    }
}
