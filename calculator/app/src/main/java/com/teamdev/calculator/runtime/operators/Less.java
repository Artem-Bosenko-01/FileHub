package com.teamdev.calculator.runtime.operators;

import com.teamdev.calculator.runtime.holder.ValueHolder;
import com.teamdev.calculator.runtime.holder.value.BooleanValueHolder;

public class Less implements Operator {

    @Override
    public ValueHolder<?> apply(ValueHolder<?> leftArgument, ValueHolder<?> rightArgument) {
        return new BooleanValueHolder((Double)leftArgument.getValue()<(Double) rightArgument.getValue());
    }
}
