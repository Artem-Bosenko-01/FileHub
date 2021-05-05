package com.teamdev.booby.compiler.fsm.variable;

import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;

import java.util.Arrays;
import java.util.List;

public class VariableValueFiniteSTateMachine extends FiniteStateMachine<ShuntingYardStack> {

    DoubleExpressionState doubleExpressionState = new DoubleExpressionState();
    BooleanExpressionState booleanExpressionState = new BooleanExpressionState();

    public VariableValueFiniteSTateMachine(){

    }

    @Override
    protected List<State<ShuntingYardStack>> getStartStates() {
        return Arrays.asList(booleanExpressionState, doubleExpressionState);
    }

    @Override
    protected List<State<ShuntingYardStack>> getFinishStates() {
        return Arrays.asList(booleanExpressionState, doubleExpressionState);
    }
}
