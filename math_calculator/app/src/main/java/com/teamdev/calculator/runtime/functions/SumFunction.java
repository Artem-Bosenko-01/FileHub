package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.List;

public class SumFunction extends Function {
    public SumFunction() {
        super("sum");
    }

    @Override
    public double apply(List<Double> arguments) {
        double summary = 0.0;
        for (Double argument : arguments) {
            summary += argument;
        }
        return summary;
    }
}
