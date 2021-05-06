package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;
import com.teamdev.calculator.runtime.holder.ValueHolder;
import com.teamdev.calculator.runtime.holder.doubletype.DoubleVisitor;

import java.util.List;
import java.util.Optional;

public class AverageFunction extends Function {

    public AverageFunction() {
        super("avg");
    }

    @Override
    public Optional<Double> apply(List<ValueHolder<?>> arguments) {
        double avg = 0.0;
        DoubleVisitor visitor = new DoubleVisitor();
        for (ValueHolder<?> argument: arguments) {
            avg+=visitor.getDoubleValue(argument);
        }
        return Optional.of(avg/arguments.size());
    }
}
