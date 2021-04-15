package com.teamdev.calculator.base.finite_state_machine;

import com.teamdev.calculator.base.InputCharacterStream;
import com.teamdev.calculator.base.state.State;

import java.util.LinkedList;
import java.util.List;

public abstract class FiniteStateMachine<T> {

    final private List<State> startStates;
    final private List<State> finishStates = new LinkedList<>();

    protected FiniteStateMachine(List<State> startStates) {
        this.startStates = startStates;
    }

    public FiniteStateMachine() {
        startStates = new LinkedList<>();
    }

    public List<State> getFinishStates() {
        return finishStates;
    }

    public abstract boolean execute(InputCharacterStream inputCharacterStream, T outputChain) throws Exception;


}
