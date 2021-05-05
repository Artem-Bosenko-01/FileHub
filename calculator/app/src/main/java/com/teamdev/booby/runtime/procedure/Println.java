package com.teamdev.booby.runtime.procedure;

import com.teamdev.calculator.runtime.Function;
import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.List;
import java.util.Optional;

public class Println extends Function {
    public Println() {
        super("println");
    }

    @Override
    public Optional<Double> apply(List<ValueHolder<?>> arguments) {
        for (ValueHolder<?> arg: arguments) {
            System.out.println(arg.getValue());
        }
        return Optional.empty();
    }
}
