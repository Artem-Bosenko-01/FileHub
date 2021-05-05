package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.List;
import java.util.Optional;

public class PiFunc extends Function {
    public PiFunc() {
        super("pi");
    }

    @Override
    public Optional<Double> apply(List<Double> arguments) {
        if(arguments.isEmpty()) return Optional.of(3.14159);
        else return Optional.empty();
    }
}
