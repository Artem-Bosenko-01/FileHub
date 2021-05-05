package com.teamdev.calculator.compiler.fsm.booleanexpression;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;

public class SymbolState extends State<StringBuilder> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {

        if(String.valueOf(characterStream.getCurrentSymbol()).matches("[<=>!]")){
            builder.append(characterStream.getCurrentSymbol());
            characterStream.increasePointer();
            return true;
        }

        return false;
    }
}
