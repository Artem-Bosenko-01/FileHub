package com.teamdev.calculator.runtime.holder.value;

import com.teamdev.calculator.runtime.holder.HolderVisitor;
import com.teamdev.calculator.runtime.holder.ValueHolder;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class DoubleValueHolder extends ValueHolder<Double> {

    public DoubleValueHolder(Double value) {
        super(value);
    }

    @Override
    public void accept(HolderVisitor visitor) {
        visitor.visit(this.getValue());
    }
}
