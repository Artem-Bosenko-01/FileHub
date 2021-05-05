package com.teamdev.booby.compiler.fsm.variable;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.compiler.fsm.booleanexpression.BooleanExpressionFiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.runtime.BooleanScope;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.holder.doubletype.DoubleValueHolder;

public class BooleanExpressionState extends State<ShuntingYardStack> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        BooleanExpressionFiniteStateMachine machine = new BooleanExpressionFiniteStateMachine();
        BooleanScope scope = new BooleanScope();
        try {
            if(machine.execute(characterStream, scope)){
                builder.pushOperand(scope.getOperator().apply(new DoubleValueHolder(scope.getOperands().get(0)), new DoubleValueHolder(scope.getOperands().get(1))));
                return true;
            }

        } catch (InvalidSymbolException | NotExistPairBracketException e) {
            e.printStackTrace();
        }
        return false;
    }
}
