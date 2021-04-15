package com.teamdev.calculator.expression;

import com.teamdev.calculator.base.InputCharacterStream;
import com.teamdev.calculator.base.state.State;

public class OperatorState extends State<Character> {
    @Override
    public void addTransition(State... transitions) {
        for (State s : transitions) {
            states.add(s);
        }
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        if(String.valueOf(characterStream.getCurrentSymbol()).matches("[-*/^+]")){
            builder.append(characterStream.getCurrentSymbol());
            characterStream.increasePointer();
            return true;
        }

        return false;
    }
}
