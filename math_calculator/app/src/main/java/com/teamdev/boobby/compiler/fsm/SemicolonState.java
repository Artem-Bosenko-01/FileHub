package com.teamdev.boobby.compiler.fsm;

import com.teamdev.boobby.runtime.ResultScope;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;

public class SemicolonState extends State<ResultScope> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ResultScope builder) {
        if(characterStream.getCurrentSymbol() == ';'){
            characterStream.increasePointer();
            return true;
        }
        return false;
    }
}
