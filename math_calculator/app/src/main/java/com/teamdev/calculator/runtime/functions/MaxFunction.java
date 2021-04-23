package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.Collections;
import java.util.List;

public class MaxFunction extends Function {
    public MaxFunction() {
        super("max");
    }

    @Override
    public double apply(List<Double> arguments) {
        return Collections.max(arguments);
    }
}
