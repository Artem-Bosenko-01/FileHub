package com.teamdev.booby.runtime;

import com.teamdev.calculator.runtime.functions.Function;
import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

public class Println implements Function {
    private final StringBuilder writer;
    public Println(StringBuilder writer) {
        super();
        this.writer = writer;
    }


    @Override
    public Optional<Double> apply(List<ValueHolder<?>> arguments) {
        for (ValueHolder<?> arg: arguments) {
            writer.append(arg.getValue());
        }
        return Optional.empty();
    }
}
