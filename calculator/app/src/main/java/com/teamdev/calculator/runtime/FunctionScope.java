package com.teamdev.calculator.runtime;

import java.util.ArrayList;
import java.util.List;

/**
 * This is parser for one type of {@link Function functions} and list of arguments for this function
 * */
public class FunctionScope {
    private Function function;
    private final List<Double> arguments = new ArrayList<>();

    public void addName(String nameFunction){
        this.function = new FunctionFactory().getFunction(nameFunction);
    }

    public void addArgument(Double argument){
        arguments.add(argument);
    }

    public List<Double> getArguments() {
        return arguments;
    }
    public Function getFunction(){
        return function;
    }
}
