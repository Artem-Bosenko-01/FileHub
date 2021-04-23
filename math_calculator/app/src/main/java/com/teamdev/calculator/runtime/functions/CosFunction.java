package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.List;

public class CosFunction extends Function {
    public CosFunction() {
        super("cos");
    }

    @Override
    public double apply(List<Double> arguments) {
        return Math.cos(arguments.get(0));
    }
}
