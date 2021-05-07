package com.teamdev.booby.runtime;

import com.teamdev.calculator.runtime.functions.Function;
import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class Println implements Function {
    public Println() {
        super();
    }

    @Override
    public Optional<Double> apply(List<ValueHolder<?>> arguments) {
        for (ValueHolder<?> arg: arguments) {
            System.out.println(arg.getValue());
        }
        return Optional.empty();
    }
}
