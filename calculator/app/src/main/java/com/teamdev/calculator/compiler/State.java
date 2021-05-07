package com.teamdev.calculator.compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *This is the basic unit in the {@link FiniteStateMachine machine}, that used to determine the possibility of transition
 *for the input symbol from {@link InputCharacterStream character stream}. Type of output chain is determined by
 * {@link T parameter}
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyDependents"})
public abstract class State<T> {
    private final List<State<T>> states = new ArrayList<>();

    @SafeVarargs
    public final void addTransition(State<T>... transitions){
        states.addAll(Arrays.asList(transitions));

    }

    public abstract boolean tryTransition(InputCharacterStream characterStream, T builder);
    public List<State<T>> getTransitions(){return Collections.unmodifiableList(states);}
}