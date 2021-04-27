package com.teamdev.boobby.compiler.fsm;

import com.teamdev.boobby.runtime.ResultScope;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;

public class InitVarState extends State<ResultScope> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ResultScope builder) {
        InitVarFiniteStateMachine machine = new InitVarFiniteStateMachine();
        StringBuilder stringBuilder = new StringBuilder();
        if(machine.execute(characterStream, stringBuilder)){

        }
        return false;
    }
}
