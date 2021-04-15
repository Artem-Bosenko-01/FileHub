package com.teamdev.calculator.number;

import com.teamdev.calculator.base.InputCharacterStream;
import com.teamdev.calculator.base.state.State;

public class DigitState extends State<Integer> {

    @Override
    public void addTransition(State<Integer>... transitions) {
        for (State<Integer> state : transitions) {
            states.add(state);
        }
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {

        if (Character.isDigit(characterStream.getCurrentSymbol())) {
            builder.append(characterStream.getCurrentSymbol());
            characterStream.increasePointer();
            return true;
        }
        return false;
    }


}
