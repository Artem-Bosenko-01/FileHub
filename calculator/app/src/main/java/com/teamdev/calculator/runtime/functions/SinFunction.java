package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.List;
import java.util.Optional;

public class SinFunction extends Function {
    public SinFunction() {
        super("sin");
    }

    @Override
    public Optional<Double> apply(List<Double> arguments) {
        return Optional.of(Math.sin(arguments.get(0)));
    }
}
