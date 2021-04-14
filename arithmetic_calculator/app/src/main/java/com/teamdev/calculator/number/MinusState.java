package com.teamdev.calculator.number;

import com.teamdev.calculator.base.InputCharacterStream;
import com.teamdev.calculator.base.state.State;

public class MinusState extends State<Character> {
    @Override
    public void addTransition(State<Character>... transitions) {

    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        if (characterStream.getCurrentSymbol() =='-') {
            builder.append(characterStream.getCurrentSymbol());
            characterStream.increasePointer();
            return true;
        }
        return false;
    }
}
