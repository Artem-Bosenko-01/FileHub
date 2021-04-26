package com.teamdev.calculator.runtime;

import java.util.List;

public abstract class Function {

    private String name;
    public Function(String name){
        this.name = name;
    }
    public String getName(){return name;}

    public abstract double apply(List<Double> arguments);
}
