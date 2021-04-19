package com.teamdev.calculator.fsm.number;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.fsm.State;

public class MinusState extends State<StringBuilder> {

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
