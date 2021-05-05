package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.List;
import java.util.Optional;

public class SumFunction extends Function {
    public SumFunction() {
        super("sum");
    }

    @Override
    public Optional<Double> apply(List<Double> arguments) {
        double summary = 0.0;
        for (Double argument : arguments) {
            summary += argument;
        }
        return Optional.of(summary);
    }
}
