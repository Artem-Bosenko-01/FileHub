package com.teamdev.booby.compiler.fsm;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.ExpressionFiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.runtime.ShuntingYardStack;


public class ExpressionStateForBooby extends State<StringBuilder> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        ExpressionFiniteStateMachine expressionFiniteStateMachine = new ExpressionFiniteStateMachine();
        ShuntingYardStack stack = new ShuntingYardStack();
        try {
            if(expressionFiniteStateMachine.execute(characterStream, stack)){
                builder.append(stack.calculate());
                return true;
            }
        } catch (InvalidSymbolException | NotExistPairBracketException e) {
            e.printStackTrace();
        }
        return false;
    }
}
