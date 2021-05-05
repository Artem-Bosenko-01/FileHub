package com.teamdev.calculator.runtime;

import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * This is parser for one type of {@link Function functions} and list of arguments for this function
 * */
public class FunctionScope implements Cloneable {
    private Function function;
    private List<ValueHolder<?>> arguments;

    public FunctionScope(){
        arguments = new ArrayList<>();
    }

    public void addName(String nameFunction){
        this.function = new FunctionFactory().getFunction(nameFunction);
    }

    public void addArgument(ValueHolder<?> argument){
        arguments.add(argument);
    }

    public List<ValueHolder<?>> getArguments() {
        return arguments;
    }
    public Function getFunction(){
        return function;
    }
}
