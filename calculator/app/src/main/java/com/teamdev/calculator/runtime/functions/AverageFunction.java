package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.List;
import java.util.Optional;

public class AverageFunction extends Function {

    public AverageFunction() {
        super("avg");
    }

    @Override
    public Optional<Double> apply(List<Double> arguments) {
        double avg = 0.0;
        for (Double argument: arguments) {
            avg+=argument;
        }
        return Optional.of(avg/arguments.size());
    }
}
