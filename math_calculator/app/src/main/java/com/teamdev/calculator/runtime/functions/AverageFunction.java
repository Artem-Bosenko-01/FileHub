package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.List;

public class AverageFunction extends Function {

    public AverageFunction() {
        super("avg");
    }

    @Override
    public double apply(List<Double> arguments) {
        double avg = 0.0;
        for (Double argument: arguments) {
            avg+=argument;
        }
        return avg/arguments.size();
    }
}
