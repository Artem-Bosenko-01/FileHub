package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class SumFunction implements Function {
    public SumFunction() {
        super();
    }

    @Override
    public Optional<Double> apply(List<ValueHolder<?>> arguments) {
        double summary = 0.0;
        for (ValueHolder<?> argument : arguments) {
            summary += (Double) argument.getValue();
        }
        return Optional.of(summary);
    }
}
