package com.teamdev.calculator.compiler.function;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.operand.CloseBracketState;
import com.teamdev.calculator.compiler.operand.OpenBracketState;
import com.teamdev.calculator.runtime.functions.FunctionScope;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Function FSM - is machine, that extends {@link FiniteStateMachine basic FSM}.
 * This is process function with or without arguments.
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyTransitiveDependencies", "ClassWithTooManyDependencies"})
public class FunctionFiniteStateMachine extends FiniteStateMachine<FunctionScope> {

    private final NameState nameState = new NameState();
    private final CloseBracketState<FunctionScope> closeBracketState = new CloseBracketState<>();

    public FunctionFiniteStateMachine(CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory) {
        Logger logger = LoggerFactory.getLogger(FunctionFiniteStateMachine.class);
        logger.info("Start Function FSM");

        OpenBracketState<FunctionScope> openBracketState = new OpenBracketState<>();
        ExpressionArgumentState expressionState = new ExpressionArgumentState(compilerFactory);
        CommaState commaState = new CommaState();

        nameState.addTransition(openBracketState);
        openBracketState.addTransition(closeBracketState, expressionState);
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
