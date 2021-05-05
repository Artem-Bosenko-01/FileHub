package com.teamdev.booby.compiler.fsm.variable;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.compiler.fsm.function.NameFiniteStateMachine;

public class NameStateForBooby extends State<StringBuilder> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder outputChain) {
        NameFiniteStateMachine nameFiniteStateMachine = new NameFiniteStateMachine();
        try {
            if(nameFiniteStateMachine.execute(characterStream, outputChain)){
                return true;
            }
        } catch (InvalidSymbolException | NotExistPairBracketException e) {
            e.printStackTrace();
        }

        return false;
    }
}
