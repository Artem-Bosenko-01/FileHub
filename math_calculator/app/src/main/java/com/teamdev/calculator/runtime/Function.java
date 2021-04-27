package com.teamdev.calculator.runtime;

import java.util.List;

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

    public abstract double apply(List<Double> arguments);
}
