package com.teamdev.calculator.fsm;

import com.teamdev.calculator.compiler.InputCharacterStream;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class State<T> {

    private List<State> states = new LinkedList<>();

    public void addTransition(State... transitions){
        states = Arrays.asList(transitions);
    }

    public abstract boolean tryTransition(InputCharacterStream characterStream, T builder);
    public List<State> getTransitions(){return states;}
}