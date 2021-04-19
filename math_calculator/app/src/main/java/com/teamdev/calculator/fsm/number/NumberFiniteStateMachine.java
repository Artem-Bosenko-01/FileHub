package com.teamdev.calculator.fsm.number;


import com.teamdev.calculator.fsm.FiniteStateMachine;
import java.util.Arrays;

public class NumberFiniteStateMachine extends FiniteStateMachine<StringBuilder> {

    public NumberFiniteStateMachine() {

        MinusState minus = new MinusState();
        DigitState integer = new DigitState();
        DotState dot = new DotState();
        DigitState decimal = new DigitState();

        minus.addTransition(integer);
        integer.addTransition(integer, dot);
        dot.addTransition(decimal);
        decimal.addTransition(decimal);

        setStartStates(Arrays.asList(minus, integer));
        setFinishStates(Arrays.asList(integer, decimal));

    }


    /*@Override
    public boolean execute(InputCharacterStream inputCharacterStream, X outputChain) throws Exception {

        while (!inputCharacterStream.isEmpty()) {
            if (inputCharacterStream.getCurrentSymbol() == '.') {
                State<Character> state = new SingleCharacterState();
                state.tryTransition(inputCharacterStream, (StringBuilder) outputChain);
            }
            *//*else if(inputCharacterStream.getCurrentSymbol() == '-'){
                State<Character> state = new MinusState();
                state.tryTransition(inputCharacterStream, builder);
            }*//*
            else if (String.valueOf(inputCharacterStream.getCurrentSymbol()).matches("[0-9]")) {
                State<Integer> state = new DigitState();
                state.tryTransition(inputCharacterStream, (StringBuilder) outputChain);
            } else {
                if (String.valueOf(inputCharacterStream.getPreviousSymbol()).matches("[0-9]")) {
                    inputCharacterStream.increasePointer();
                    return true;
                } else {
                    ((StringBuilder) outputChain).delete(0, inputCharacterStream.getPointer());
                    throw new RuntimeException("you input invalid value");
                }
            }
        }

        return true;
    }*/
}
