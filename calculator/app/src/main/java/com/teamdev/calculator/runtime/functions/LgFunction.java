package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;
import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.List;
import java.util.Optional;

public class LgFunction extends Function {
    public LgFunction() {
        super("log10");
    }

    @Override
    public Optional<Double> apply(List<ValueHolder<?>> arguments) {
        return Optional.of(Math.log10((Double) arguments.get(0).getValue()));
    }
}
