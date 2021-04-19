package com.teamdev.calculator.fsm;

import com.teamdev.calculator.compiler.InputCharacterStream;

import java.util.LinkedList;
import java.util.List;

public abstract class FiniteStateMachine<T> {

    private List<State> startStates;
    private List<State> finishStates = new LinkedList<>();

    public void setStartStates(List<State> startStates) {
        this.startStates = startStates;
    }

    public void setFinishStates(List<State> finishStates) {
        this.finishStates = finishStates;
    }


    private State getNextState(State state){

        List<State> states = state.getTransitions();
        for (State transition: states) {

            return transition;
        }
        return null;
    }

    public boolean execute(InputCharacterStream inputCharacterStream, T outputChain) throws Exception{

        State currentPosition = startStates.get(0);


        while (!inputCharacterStream.isEmpty()){



        }

        if (finishStates.contains(currentPosition)) {

            return true;
        }else {
            System.out.println("smone go wrong");
            return false;
        }
    }


}
