package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class PiFunc implements Function {
    public PiFunc() {
        super();
    }

    @Override
    public Optional<Double> apply(List<ValueHolder<?>> arguments) {
        if(arguments.isEmpty()) return Optional.of(StrictMath.PI);
        else return Optional.empty();
    }
}
