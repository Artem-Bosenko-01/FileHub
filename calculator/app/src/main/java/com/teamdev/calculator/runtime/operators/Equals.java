package com.teamdev.calculator.runtime.operators;

import com.teamdev.calculator.runtime.holder.ValueHolder;
import com.teamdev.calculator.runtime.holder.value.BooleanValueHolder;

public class Equals implements Operator {

    @Override
    public ValueHolder<?> apply(ValueHolder<?> leftArgument, ValueHolder<?> rightArgument) {
        return new BooleanValueHolder(leftArgument.getValue().equals(rightArgument.getValue()));
    }

}
