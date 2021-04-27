package com.teamdev.boobby.compiler.fsm;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.ExpressionFiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;


public class ExpressionStateForBooby extends State<StringBuilder> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        ExpressionFiniteStateMachine expressionFiniteStateMachine = new ExpressionFiniteStateMachine();
        ShuntingYardStack stack = new ShuntingYardStack();
        if(expressionFiniteStateMachine.execute(characterStream, stack)){
            builder.append(stack.calculate());
            return true;
        }
        return false;
    }
}
