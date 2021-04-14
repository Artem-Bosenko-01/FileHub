package com.teamdev.calculator.base.finite_state_machine;

import com.teamdev.calculator.base.InputCharacterStream;
import com.teamdev.calculator.base.io.Scanner;
import com.teamdev.calculator.base.state.State;
import com.teamdev.calculator.number.DigitState;
import com.teamdev.calculator.number.MinusState;
import com.teamdev.calculator.number.SingleCharacterState;

import java.util.LinkedList;
import java.util.List;

public abstract class FiniteStateMachine {

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

    public abstract boolean execute(InputCharacterStream inputCharacterStream, StringBuilder outputChain) throws Exception;


    public State getNextState(char symbol){
        for (State s:startStates) {
            if (symbol == '.') {
                return new SingleCharacterState();
            }
            else if(symbol == '-'){
                return new MinusState();
            }
            else if (String.valueOf(symbol).matches("[0-9]")) {
                return new DigitState();
            }
        }
        throw new RuntimeException("Invalid symbol");
    }
}
