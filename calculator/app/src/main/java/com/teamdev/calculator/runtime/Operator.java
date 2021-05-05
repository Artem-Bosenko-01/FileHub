package com.teamdev.calculator.runtime;

import com.teamdev.calculator.runtime.holder.ValueHolder;

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
