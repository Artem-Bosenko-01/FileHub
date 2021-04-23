package com.teamdev.calculator.compiler.fsm.function;

import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.runtime.FunctionScope;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Collections;

public class FunctionFiniteStateMachine extends FiniteStateMachine<FunctionScope> {

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(FunctionFiniteStateMachine.class);

    public FunctionFiniteStateMachine() {
        logger.info("Start Function FSM");
        NameState nameState = new NameState();
        OpenBracketFunctionState openBracketState = new OpenBracketFunctionState();
        ExpressionArgumentState expressionState = new ExpressionArgumentState();
        CloseBracketFunctionState closeBracketState = new CloseBracketFunctionState();
        CommaState commaState = new CommaState();

        nameState.addTransition(openBracketState);
        openBracketState.addTransition(expressionState);
        expressionState.addTransition(commaState, closeBracketState);
        commaState.addTransition(expressionState);

        setStartStates(Collections.singletonList(nameState));
        setFinishStates(Collections.singletonList(closeBracketState));
    }
}
