package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.holder.ValueHolder;
import com.teamdev.calculator.runtime.holder.value.DoubleVisitor;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class AverageFunction implements Function {

    public AverageFunction() {
        super();
    }

    @Override
    public Optional<Double> apply(List<ValueHolder<?>> arguments) {
        double avg = 0.0;
        DoubleVisitor visitor = new DoubleVisitor();
        for (ValueHolder<?> argument: arguments) {
            Optional<Double> value = visitor.getDoubleValue(argument);
            if(value.isPresent()){
                avg+= value.get();
            }
        }
        return Optional.of(avg/arguments.size());
    }
}
