package com.teamdev.booby.compiler.fsm.variable;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.runtime.ShuntingYardStack;

public class VariableValueState extends State<StringBuilder> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        VariableValueFiniteSTateMachine machine = new VariableValueFiniteSTateMachine();
        ShuntingYardStack stack = new ShuntingYardStack();
        try {
            if(machine.execute(characterStream, stack)){
                builder.append(stack.calculate().getValue());
                return true;
            }


        } catch (InvalidSymbolException | NotExistPairBracketException e) {
            e.printStackTrace();
        }
        return false;
    }
}
