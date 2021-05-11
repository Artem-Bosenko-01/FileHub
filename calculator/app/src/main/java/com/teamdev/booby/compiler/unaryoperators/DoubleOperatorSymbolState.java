package com.teamdev.booby.compiler.unaryoperators;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class DoubleOperatorSymbolState extends State<StringBuilder> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        if(String.valueOf(characterStream.getCurrentSymbol()).matches("[+-]")){
            builder.append(characterStream.getCurrentSymbol());
            characterStream.increasePointer();
            return true;
        }
        return false;
    }
}
