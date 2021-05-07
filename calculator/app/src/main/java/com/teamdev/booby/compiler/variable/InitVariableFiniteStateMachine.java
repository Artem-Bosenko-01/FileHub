package com.teamdev.booby.compiler.variable;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class InitVariableFiniteStateMachine extends FiniteStateMachine<StringBuilder> {

    private final NameStateForBooby nameState = new NameStateForBooby();
    private final VariableValueState expressionState;

    public InitVariableFiniteStateMachine(CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory){

        AttributionState attributionState = new AttributionState();
        expressionState = new VariableValueState(compilerFactory);

        nameState.addTransition(attributionState);
        attributionState.addTransition(expressionState);

    }

    @Override
    public List<State<StringBuilder>> getStartStates() {
        return Collections.singletonList(nameState);
    }

    @Override
    public List<State<StringBuilder>> getFinishStates() {
        return Collections.singletonList(expressionState);
    }
}
