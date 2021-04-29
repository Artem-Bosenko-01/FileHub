package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;

import java.util.Collections;
import java.util.List;

public class MinFunction extends Function {
    public MinFunction() {
        super("min");
    }

    @Override
    public double apply(List<Double> arguments) {
        return Collections.min(arguments);
    }
}
