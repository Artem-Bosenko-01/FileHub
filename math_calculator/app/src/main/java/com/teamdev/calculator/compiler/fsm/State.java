package com.teamdev.calculator.compiler.fsm;

import com.teamdev.calculator.compiler.InputCharacterStream;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class State<T> {
    private List<State<T>> states = new LinkedList<>();

    @SafeVarargs
    public final void addTransition(State<T>... transitions){
        states = Arrays.asList(transitions);
    }

    public abstract boolean tryTransition(InputCharacterStream characterStream, T builder);
    public List<State<T>> getTransitions(){return Collections.unmodifiableList(states);}
}