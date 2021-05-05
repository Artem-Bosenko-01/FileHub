package com.teamdev.calculator.runtime;

import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.List;
import java.util.Optional;

/**
 * This is basic type of expression,that implement some of
 * <a href="https://www.mathsisfun.com/sets/functions-common.html">mathematical functions</a>.
 * It is used in {@link FunctionCommand function command}
 * */
public abstract class Function {

    private final String name;
    public Function(String name){
        this.name = name;
    }

    public abstract Optional<Double> apply(List<ValueHolder<?>> arguments);
}
