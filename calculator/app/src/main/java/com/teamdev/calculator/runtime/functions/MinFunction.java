package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.holder.ValueHolder;
import com.teamdev.calculator.runtime.holder.value.DoubleVisitor;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class MinFunction implements Function {
    public MinFunction() {
        super();
    }

    @Override
    public Optional<Double> apply(List<ValueHolder<?>> arguments) {
        DoubleVisitor visitor = new DoubleVisitor();
        ValueHolder<?> holder = arguments.stream()
                .min(Comparator.comparingDouble(arg -> visitor.getDoubleValue(arg).get()))
                .orElse(null);

        if (holder != null) {
            return visitor.getDoubleValue(holder);
        }
        return Optional.empty();
    }
}
