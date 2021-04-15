package com.teamdev.calculator.factory;

import com.teamdev.calculator.base.finite_state_machine.FiniteStateMachine;
import com.teamdev.calculator.expression.ExpressionFiniteStateMachine;
import com.teamdev.calculator.expression.ShuntingYardStack;
import com.teamdev.calculator.number.NumberFiniteStateMachine;

public class FiniteStateMachineFactory {

    public FiniteStateMachine create(FiniteStateMachineType type){
        switch (type){
            case NUMBER: return new NumberFiniteStateMachine<StringBuilder>();
            case EXPRESSION: return new ExpressionFiniteStateMachine<ShuntingYardStack>();
            default: throw new RuntimeException("Invalid input string");
        }
    }

}
