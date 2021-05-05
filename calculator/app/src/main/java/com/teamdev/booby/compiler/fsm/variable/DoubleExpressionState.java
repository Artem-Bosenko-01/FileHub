package com.teamdev.booby.compiler.fsm.variable;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.ExpressionFiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.runtime.ShuntingYardStack;


public class DoubleExpressionState extends State<ShuntingYardStack> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        ExpressionFiniteStateMachine expressionFiniteStateMachine = new ExpressionFiniteStateMachine();
        try {
            if(expressionFiniteStateMachine.execute(characterStream, builder)){
                return true;
            }
        } catch (InvalidSymbolException | NotExistPairBracketException e) {
            e.printStackTrace();
        }
        return false;
    }
}
