package com.teamdev.calculator.runtime.operators;

import com.teamdev.calculator.runtime.Operator;
import com.teamdev.calculator.runtime.holder.ValueHolder;
import com.teamdev.calculator.runtime.holder.booleantype.BooleanValueHolder;

public class More implements Operator {

    @Override
    public ValueHolder<?> apply(ValueHolder<?> leftArgument, ValueHolder<?> rightArgument) {
        return new BooleanValueHolder((Double) leftArgument.getValue() > (Double) rightArgument.getValue());
    }
}
