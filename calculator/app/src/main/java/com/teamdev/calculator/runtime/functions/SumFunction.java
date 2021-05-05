package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;
import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.List;
import java.util.Optional;

public class SumFunction extends Function {
    public SumFunction() {
        super("sum");
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
