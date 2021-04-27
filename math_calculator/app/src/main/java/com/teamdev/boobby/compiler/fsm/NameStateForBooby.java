package com.teamdev.boobby.compiler.fsm;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.compiler.fsm.function.NameFiniteStateMachine;

public class NameStateForBooby extends State<StringBuilder> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder outputChain) {
        NameFiniteStateMachine nameFiniteStateMachine = new NameFiniteStateMachine();
        StringBuilder builder = new StringBuilder();
        if(nameFiniteStateMachine.execute(characterStream, builder)){
            outputChain.append(builder);
            return true;
        }

        return false;
    }
}
