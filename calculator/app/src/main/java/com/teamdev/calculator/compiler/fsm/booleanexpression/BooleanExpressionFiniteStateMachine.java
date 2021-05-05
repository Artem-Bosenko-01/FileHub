package com.teamdev.calculator.compiler.fsm.booleanexpression;

import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.BooleanScope;

import java.util.Collections;
import java.util.List;

public class BooleanExpressionFiniteStateMachine extends FiniteStateMachine<BooleanScope> {

    BooleanOperandState leftOperandState = new BooleanOperandState();
    BooleanOperatorState operatorState = new BooleanOperatorState();
    BooleanOperandState rightOperandState = new BooleanOperandState();

    public BooleanExpressionFiniteStateMachine(){
        leftOperandState.addTransition(operatorState);
        operatorState.addTransition(rightOperandState);
    }

    @Override
    protected List<State<BooleanScope>> getStartStates() {
        return Collections.singletonList(leftOperandState);
    }

    @Override
    protected List<State<BooleanScope>> getFinishStates() {
        return Collections.singletonList(rightOperandState);
    }
}
