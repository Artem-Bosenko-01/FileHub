package com.teamdev.calculator.compiler.fsm;

import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Collections;
import java.util.List;

/**
 *This is {@link FiniteStateMachine machine}, that used to detect two operands with binary operator
 * */
public class ExpressionFiniteStateMachine extends FiniteStateMachine<ShuntingYardStack> {


    private final Logger logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(ExpressionFiniteStateMachine.class);
    private final OperandState operand = new OperandState();
    private final OperatorState operator = new OperatorState();

    public ExpressionFiniteStateMachine() {
        logger.info("Create Expression FSM");

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