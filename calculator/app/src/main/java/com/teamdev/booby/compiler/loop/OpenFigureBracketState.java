package com.teamdev.booby.compiler.loop;

import com.teamdev.booby.runtime.ForOutputChain;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;

public class OpenFigureBracketState extends State<ForOutputChain > {

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ForOutputChain builder) {

        if(characterStream.getCurrentSymbol()=='{'){
            characterStream.increasePointer();
            return true;
        }
        return false;
    }
}
