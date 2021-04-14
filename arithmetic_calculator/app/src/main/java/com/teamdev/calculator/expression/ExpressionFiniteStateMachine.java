package com.teamdev.calculator.expression;

import com.teamdev.calculator.base.InputCharacterStream;
import com.teamdev.calculator.base.finite_state_machine.FiniteStateMachine;
import com.teamdev.calculator.base.state.State;

import java.util.List;

public class ExpressionFiniteStateMachine extends FiniteStateMachine {

    protected ExpressionFiniteStateMachine(List<State> startStates) {
        super(startStates);
    }

    @Override
    public boolean execute(InputCharacterStream inputCharacterStream, StringBuilder outputChain) {
        return false;
    }
}
