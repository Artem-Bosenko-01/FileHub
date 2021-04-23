package com.teamdev.calculator.runtime;

public interface Command<T> {
    void execute(T stack);
}
