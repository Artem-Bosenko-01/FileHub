package com.teamdev.calculator.runtime;

public interface Operator<T> extends Comparable<Operator<T>>{
    default int getPriority(){
        return 0;
    }

    T apply(double leftArgument, double rightArgument);

    @Override
    default int compareTo(Operator<T> o){
        return Integer.compare(getPriority(),o.getPriority());
    }
}
