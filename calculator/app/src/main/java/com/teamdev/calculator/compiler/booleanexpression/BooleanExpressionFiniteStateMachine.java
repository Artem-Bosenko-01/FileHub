package com.teamdev.calculator.compiler.booleanexpression;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.operators.BooleanScope;
import com.teamdev.calculator.runtime.ShuntingYardStack;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class BooleanExpressionFiniteStateMachine extends FiniteStateMachine<BooleanScope> {

    private final BooleanOperandState leftOperandState;
    private final BooleanOperandState rightOperandState;

    public BooleanExpressionFiniteStateMachine(CompilerFactory<ShuntingYardStack> compilerFactory) {

        BooleanOperatorState operatorState = new BooleanOperatorState();
        leftOperandState = new BooleanOperandState(compilerFactory);
        rightOperandState = new BooleanOperandState(compilerFactory);

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
