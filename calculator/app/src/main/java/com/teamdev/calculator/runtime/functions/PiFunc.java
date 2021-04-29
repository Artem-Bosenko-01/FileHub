package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.List;

public class PiFunc extends Function {
    public PiFunc() {
        super("pi");
    }

    @Override
    public double apply(List<Double> arguments) {
        if(arguments.isEmpty()) return 3.14159;
        else throw new RuntimeException();
    }
}
