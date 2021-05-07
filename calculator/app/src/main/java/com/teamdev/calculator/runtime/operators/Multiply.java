package com.teamdev.calculator.runtime.operators;

import com.teamdev.calculator.runtime.holder.ValueHolder;
import com.teamdev.calculator.runtime.holder.value.DoubleValueHolder;

/**
 * Note: this class has a natural ordering that is inconsistent with equals.
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class Multiply implements Operator {
    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public ValueHolder<?> apply(ValueHolder<?> leftArgument, ValueHolder<?> rightArgument) {
        return new DoubleValueHolder((Double) leftArgument.getValue()*(Double) rightArgument.getValue());
    }


}
