package com.teamdev.booby.compiler.variable;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.operand.PostfixOperatorState;
import com.teamdev.calculator.compiler.operand.PrefixOperatorState;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * This is machine, that processes double or boolean value for {@link InitVariableFiniteStateMachine init variable FSM}.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class VariableValueFiniteSTateMachine extends FiniteStateMachine<ShuntingYardStack> {
    private final DoubleExpressionState doubleExpressionState;
    private final PrefixOperatorState prefixOperatorState = new PrefixOperatorState();
    private final PostfixOperatorState postfixOperatorState = new PostfixOperatorState();
    private final BooleanExpressionState booleanExpressionState;
    private final Logger logger = LoggerFactory.getLogger(VariableValueFiniteSTateMachine.class);

    public VariableValueFiniteSTateMachine(CompilerFactory<ShuntingYardStack> compilerFactory){
        logger.info("Start Variable value FSM");
        doubleExpressionState = new DoubleExpressionState(compilerFactory);
        booleanExpressionState = new BooleanExpressionState(compilerFactory);
    }

    @Override
    protected List<State<ShuntingYardStack>> getStartStates() {
        return Arrays.asList(prefixOperatorState, postfixOperatorState,booleanExpressionState, doubleExpressionState);
    }

    @Override
    protected List<State<ShuntingYardStack>> getFinishStates() {
        return Arrays.asList(booleanExpressionState, doubleExpressionState, prefixOperatorState,postfixOperatorState);
    }
}
