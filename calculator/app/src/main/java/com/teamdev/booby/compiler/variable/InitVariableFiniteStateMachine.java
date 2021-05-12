package com.teamdev.booby.compiler.variable;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * This is machine, that define name and value of variable for
 * {@link com.teamdev.booby.runtime.RuntimeEnvironment runtime environment}.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class InitVariableFiniteStateMachine extends FiniteStateMachine<StringBuilder> {

    private final NameStateForBooby nameState = new NameStateForBooby();
    private final VariableValueState expressionState;
    private final Logger logger = LoggerFactory.getLogger(InitVariableFiniteStateMachine.class);

    public InitVariableFiniteStateMachine(CompilerFactory<ShuntingYardStack> compilerFactory){
        logger.info("Start init variable FSM");
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
