package com.teamdev.calculator.number;

import com.teamdev.calculator.base.InputCharacterStream;
import com.teamdev.calculator.base.finite_state_machine.FiniteStateMachine;
import com.teamdev.calculator.base.io.Scanner;
import com.teamdev.calculator.base.state.State;

import java.util.List;

public class NumberFiniteStateMachine<X> extends FiniteStateMachine<X> {

    public NumberFiniteStateMachine() {
        super();
    }

    public NumberFiniteStateMachine(List<State> startStates, List<State> finishState) {
        super(startStates);
    }




    @Override
    public boolean execute(InputCharacterStream inputCharacterStream, X outputChain) throws Exception {

        StringBuilder builder = (StringBuilder) outputChain;

        while (!inputCharacterStream.isEmpty()) {
            if (inputCharacterStream.getCurrentSymbol() == '.') {
                State<Character> state = new SingleCharacterState();
                state.tryTransition(inputCharacterStream, builder);
            }
            /*else if(inputCharacterStream.getCurrentSymbol() == '-'){
                State<Character> state = new MinusState();
                state.tryTransition(inputCharacterStream, builder);
            }*/
            else if (String.valueOf(inputCharacterStream.getCurrentSymbol()).matches("[0-9]")) {
                State<Integer> state = new DigitState();
                state.tryTransition(inputCharacterStream, builder);
            } else {
                if (String.valueOf(inputCharacterStream.getPreviousSymbol()).matches("[0-9]")) {
                    inputCharacterStream.increasePointer();
                    return true;
                } else {
                    builder.delete(0, inputCharacterStream.getPointer());
                    throw new RuntimeException("you input invalid value");
                }
            }
        }

        return true;
    }
}
