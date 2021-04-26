package com.teamdev.calculator.compiler.fsm;

import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Collections;

public class ExpressionFiniteStateMachine extends FiniteStateMachine<ShuntingYardStack> {

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(ExpressionFiniteStateMachine.class);

    public ExpressionFiniteStateMachine() {
        logger.info("Create Expression FSM");
        OperandState operand = new OperandState();
        OperatorState operator = new OperatorState();

        operand.addTransition(operator);
        operator.addTransition(operand);

        setStartStates(Collections.singletonList(operand));
        setFinishStates(Collections.singletonList(operand));
    }

}