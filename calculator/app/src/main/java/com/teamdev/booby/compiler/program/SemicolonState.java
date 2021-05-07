package com.teamdev.booby.compiler.program;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;

public class SemicolonState extends State<RuntimeEnvironment> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, RuntimeEnvironment builder) {
        if(characterStream.getCurrentSymbol() == ';'){
            characterStream.increasePointer();
            return true;
        }
        return false;
    }
}
