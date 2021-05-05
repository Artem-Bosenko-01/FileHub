package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;
import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.List;
import java.util.Optional;

public class LogFunction extends Function {
    public LogFunction() {
        super("log");
    }

    @Override
    public Optional<Double> apply(List<ValueHolder<?>> arguments) {
        return Optional.of(Math.log((Double) arguments.get(0).getValue()));
    }
}
