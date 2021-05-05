package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.List;
import java.util.Optional;

public class CosFunction extends Function {
    public CosFunction() {
        super("cos");
    }

    @Override
    public Optional<Double> apply(List<Double> arguments) {
        return Optional.of(Math.cos(arguments.get(0)));
    }
}
