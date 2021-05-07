package com.teamdev.booby.compiler.variable;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class VariableValueFiniteSTateMachine extends FiniteStateMachine<ShuntingYardStack> {
    private final DoubleExpressionState doubleExpressionState;
    private final BooleanExpressionState booleanExpressionState;

    public VariableValueFiniteSTateMachine(CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory){
        doubleExpressionState = new DoubleExpressionState(compilerFactory);
        booleanExpressionState = new BooleanExpressionState(compilerFactory);
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
