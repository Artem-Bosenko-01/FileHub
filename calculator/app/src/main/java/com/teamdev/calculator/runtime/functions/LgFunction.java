package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.List;
import java.util.Optional;

public class LgFunction extends Function {
    public LgFunction() {
        super("log10");
    }

    @Override
    public Optional<Double> apply(List<Double> arguments) {
        return Optional.of(Math.log10(arguments.get(0)));
    }
}
