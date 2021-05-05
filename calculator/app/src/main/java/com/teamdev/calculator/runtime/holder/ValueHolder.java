package com.teamdev.calculator.runtime.holder;

public abstract class ValueHolder <T>{
    private final T value;

    public ValueHolder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public abstract void accept(HolderVisitor visitor);
}
