package com.teamdev.calculator.number;

import com.teamdev.calculator.base.InputCharacterStream;
import com.teamdev.calculator.base.finite_state_machine.FiniteStateMachine;
import com.teamdev.calculator.base.io.Scanner;
import com.teamdev.calculator.base.state.State;


import java.util.List;

public class NumberFiniteStateMachine extends FiniteStateMachine {

    public NumberFiniteStateMachine() {
        super();
    }

    public NumberFiniteStateMachine(List<State> startStates, List<State> finishState) {
        super(startStates);
    }




    @Override
    public boolean execute(InputCharacterStream inputCharacterStream, StringBuilder outputChain) throws Exception {

        while (!inputCharacterStream.isEmpty()) {
            if (inputCharacterStream.getCurrentSymbol() == '.') {
                State<Character> state = new SingleCharacterState();
                state.tryTransition(inputCharacterStream, outputChain);
            }
            else if(inputCharacterStream.getCurrentSymbol() == '-'){
                State<Character> state = new MinusState();
                state.tryTransition(inputCharacterStream, outputChain);
            }
            else if (String.valueOf(inputCharacterStream.getCurrentSymbol()).matches("[0-9]")) {
                State<Integer> state = new DigitState();
                state.tryTransition(inputCharacterStream, outputChain);
            } else {
                if (String.valueOf(inputCharacterStream.getCurrentSymbol()).matches("[0-9]")) {
                    Scanner.output(outputChain.toString());
                    return false;
                } else {
                    outputChain.delete(0, inputCharacterStream.getPointer());
                    throw new RuntimeException("you input invalid value");
                }
            }
        }

        Scanner.output(outputChain.toString());
        return true;
    }
}
