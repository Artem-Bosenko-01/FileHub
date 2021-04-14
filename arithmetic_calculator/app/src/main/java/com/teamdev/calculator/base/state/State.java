package com.teamdev.calculator.base.state;

import com.teamdev.calculator.base.InputCharacterStream;

import java.util.LinkedList;
import java.util.List;

public abstract class State<T> {

    protected final List<State<T>> states = new LinkedList<>();

    public abstract void addTransition(State<T>... transitions);

    public abstract boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder);
    public List<State<T>> getTransitions(){return states;}
}