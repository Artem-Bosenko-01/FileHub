package com.teamdev.booby.compiler.fsm.variable;

import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;

import java.util.Collections;
import java.util.List;

public class InitVariableFiniteStateMachine extends FiniteStateMachine<StringBuilder> {

    private final NameStateForBooby nameState = new NameStateForBooby();
    private final AttributionState attributionState = new AttributionState();
    private final VariableValueState expressionState = new VariableValueState();

    public InitVariableFiniteStateMachine(){

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
