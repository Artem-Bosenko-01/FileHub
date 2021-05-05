package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;
import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.List;
import java.util.Optional;

public class CosFunction extends Function {
    public CosFunction() {
        super("cos");
    }

    @Override
    public Optional<Double> apply(List<ValueHolder<?>> arguments) {
        return Optional.of(Math.cos((Double) arguments.get(0).getValue()));
    }
}
