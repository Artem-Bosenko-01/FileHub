package com.teamdev.calculator.runtime.holder.booleantype;

import com.teamdev.calculator.runtime.holder.HolderVisitor;
import com.teamdev.calculator.runtime.holder.ValueHolder;

public class BooleanValueHolder extends ValueHolder<Boolean> {

    public BooleanValueHolder(Boolean value) {
        super(value);
    }

    @Override
    public void accept(HolderVisitor visitor) {
        visitor.visit(this.getValue());
    }
}
