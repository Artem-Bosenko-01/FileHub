package com.teamdev.boobby.compiler.fsm;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;

public class AttributionState extends State<StringBuilder> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {

        if(characterStream.getCurrentSymbol() == '='){
            characterStream.increasePointer();
            return true;
        }
        return false;
    }
}
