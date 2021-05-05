package com.teamdev.booby.compiler.fsm.variable;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;

public class AttributionState extends State<StringBuilder> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {

        if(characterStream.getCurrentSymbol() == '='){
            builder.append(characterStream.getCurrentSymbol());
            characterStream.increasePointer();
            return true;
        }
        return false;
    }
}
