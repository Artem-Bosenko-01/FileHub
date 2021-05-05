package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MaxFunction extends Function {
    public MaxFunction() {
        super("max");
    }

    @Override
    public Optional<Double> apply(List<Double> arguments) {
        return Optional.of(Collections.max(arguments));
    }
}
