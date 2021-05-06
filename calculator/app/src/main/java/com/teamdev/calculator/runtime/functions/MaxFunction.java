package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;
import com.teamdev.calculator.runtime.holder.ValueHolder;
import com.teamdev.calculator.runtime.holder.doubletype.DoubleVisitor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MaxFunction extends Function {
    public MaxFunction() {
        super("max");
    }

    @Override
    public Optional<Double> apply(List<ValueHolder<?>> arguments) {
        DoubleVisitor visitor = new DoubleVisitor();
        ValueHolder<?> holder = arguments.stream().max(Comparator.comparingDouble(visitor::getDoubleValue)).get();

        return Optional.of(visitor.getDoubleValue(holder));
    }
}
