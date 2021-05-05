package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MinFunction extends Function {
    public MinFunction() {
        super("min");
    }

    @Override
    public Optional<Double> apply(List<Double> arguments) {
        return Optional.of(Collections.min(arguments));
    }
}
