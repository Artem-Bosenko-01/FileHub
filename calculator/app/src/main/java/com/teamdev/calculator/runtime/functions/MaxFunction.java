package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.Function;
import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MaxFunction extends Function {
    public MaxFunction() {
        super("max");
    }

    @Override
    public Optional<Double> apply(List<ValueHolder<?>> arguments) {
        Optional<Double> maxValue = Optional.empty();
        for (int i = 0; i < arguments.size(); i++) {
            if((Double)arguments.get(i).getValue()>(Double) arguments.get(i+1).getValue()){
                maxValue = Optional.of((Double)arguments.get(i).getValue());
            }
        }
        return maxValue;
    }
}
