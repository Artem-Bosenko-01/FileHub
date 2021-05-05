package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;
import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MinFunction extends Function {
    public MinFunction() {
        super("min");
    }

    @Override
    public Optional<Double> apply(List<ValueHolder<?>> arguments) {
        Optional<Double> minValue = Optional.empty();
        for (int i = 0; i < arguments.size(); i++) {
            if((Double)arguments.get(i).getValue()<(Double) arguments.get(i+1).getValue()){
                minValue = Optional.of((Double)arguments.get(i).getValue());
            }
        }
        return minValue;
    }
}
