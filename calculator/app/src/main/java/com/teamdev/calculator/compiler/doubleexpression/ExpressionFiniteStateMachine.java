package com.teamdev.calculator.compiler.doubleexpression;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 *This is {@link FiniteStateMachine machine}, that used to detect two operands with binary operator.
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyTransitiveDependencies"})
public class ExpressionFiniteStateMachine extends FiniteStateMachine<ShuntingYardStack> {
    private final OperandState operand;

    public ExpressionFiniteStateMachine(CompilerFactory<ShuntingYardStack> compilerFactory) {
        Logger logger = LoggerFactory.getLogger(ExpressionFiniteStateMachine.class);
        logger.info("Create Expression FSM");

        OperatorState operator = new OperatorState();
        operand = new OperandState(compilerFactory);

        operand.addTransition(operator);
        operator.addTransition(operand);
    }

    @Override
    public List<State<ShuntingYardStack>> getStartStates() {
        return Collections.singletonList(operand);
    }

    @Override
    public List<State<ShuntingYardStack>> getFinishStates() {
        return Collections.singletonList(operand);
    }
}