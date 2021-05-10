package com.teamdev.booby.compiler.loop;

import com.teamdev.booby.runtime.ForOutputChain;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.function.NameFiniteStateMachine;

public class LoopNameState extends State<ForOutputChain> {

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ForOutputChain outputChain) {

        NameFiniteStateMachine nameFiniteStateMachine = new NameFiniteStateMachine();
        StringBuilder builder = new StringBuilder(10);
        if (nameFiniteStateMachine.execute(characterStream, builder)) {
            return builder.toString().equals("for");
        }

        return false;
    }
}
