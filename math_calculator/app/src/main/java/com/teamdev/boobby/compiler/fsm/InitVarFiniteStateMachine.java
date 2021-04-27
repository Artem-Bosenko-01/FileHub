package com.teamdev.boobby.compiler.fsm;

import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;

import java.util.Collections;
import java.util.List;

public class InitVarFiniteStateMachine extends FiniteStateMachine<StringBuilder> {

    private final NameStateForBooby nameState = new NameStateForBooby();
    private final AttributionState attributionState = new AttributionState();
    private final ExpressionStateForBooby expressionState = new ExpressionStateForBooby();

    public InitVarFiniteStateMachine(){

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
