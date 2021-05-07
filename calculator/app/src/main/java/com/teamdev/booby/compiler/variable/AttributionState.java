package com.teamdev.booby.compiler.variable;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;

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
