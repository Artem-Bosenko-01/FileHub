package com.teamdev.calculator.compiler.fsm.function;

import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.compiler.fsm.operand.CloseBracketState;
import com.teamdev.calculator.compiler.fsm.operand.OpenBracketState;
import com.teamdev.calculator.runtime.FunctionScope;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Function FSM - is machine, that extends {@link FiniteStateMachine basic FSM}.
 * This is process function with or without arguments.
 * */
public class FunctionFiniteStateMachine extends FiniteStateMachine<FunctionScope> {

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(FunctionFiniteStateMachine.class);
    private final NameState nameState = new NameState();
    private final OpenBracketState<FunctionScope> openBracketState = new OpenBracketState<>();
    private final ExpressionArgumentState expressionState = new ExpressionArgumentState();
    private final CloseBracketState<FunctionScope> closeBracketState = new CloseBracketState<>();
    private final CommaState commaState = new CommaState();

    public FunctionFiniteStateMachine() {
        logger.info("Start Function FSM");

        nameState.addTransition(openBracketState);
        openBracketState.addTransition(closeBracketState,expressionState);
        expressionState.addTransition(commaState, closeBracketState);
        commaState.addTransition(expressionState);

    }

    @Override
    public List<State<FunctionScope>> getStartStates() {
        return Collections.singletonList(nameState);
    }

    @Override
    public List<State<FunctionScope>> getFinishStates() {
        return Collections.singletonList(closeBracketState);
    }
}
