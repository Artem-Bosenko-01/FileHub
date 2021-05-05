package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.List;
import java.util.Optional;

public class LogFunction extends Function {
    public LogFunction() {
        super("log");
    }

    @Override
    public Optional<Double> apply(List<Double> arguments) {
        return Optional.of(Math.log(arguments.get(0)));
    }
}
