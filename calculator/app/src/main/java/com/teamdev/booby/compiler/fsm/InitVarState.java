package com.teamdev.booby.compiler.fsm;

import com.teamdev.booby.runtime.ResultScope;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;

public class InitVarState extends State<ResultScope> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ResultScope builder) {
        InitVarFiniteStateMachine machine = new InitVarFiniteStateMachine();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if(machine.execute(characterStream, stringBuilder)){

            }
        } catch (InvalidSymbolException | NotExistPairBracketException e) {
            e.printStackTrace();
        }
        return false;
    }
}
