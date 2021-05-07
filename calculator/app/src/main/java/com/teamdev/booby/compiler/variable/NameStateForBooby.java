package com.teamdev.booby.compiler.variable;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.function.NameFiniteStateMachine;

public class NameStateForBooby extends State<StringBuilder> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder outputChain) {
        NameFiniteStateMachine nameFiniteStateMachine = new NameFiniteStateMachine();
        return nameFiniteStateMachine.execute(characterStream, outputChain);
    }
}
