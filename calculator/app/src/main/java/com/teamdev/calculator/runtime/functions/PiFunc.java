package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;
import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.List;
import java.util.Optional;

public class PiFunc extends Function {
    public PiFunc() {
        super("pi");
    }

    @Override
    public Optional<Double> apply(List<ValueHolder<?>> arguments) {
        if(arguments.isEmpty()) return Optional.of(3.14159);
        else return Optional.empty();
    }
}
