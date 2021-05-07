package com.teamdev.calculator.runtime.operators;

import com.teamdev.calculator.runtime.holder.ValueHolder;

@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyDependents"})
public interface Operator extends Comparable<Operator>{
    default int getPriority(){
        return 0;
    }

    ValueHolder<?> apply(ValueHolder<?> leftArgument, ValueHolder<?> rightArgument);

    @Override
    default int compareTo(Operator o){
        return Integer.compare(getPriority(),o.getPriority());
    }
}
