package com.teamdev.calculator.runtime.holder;

@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyDependents"})
public abstract class ValueHolder <T>{
    private final T value;

    protected ValueHolder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public abstract void accept(HolderVisitor visitor);
}
