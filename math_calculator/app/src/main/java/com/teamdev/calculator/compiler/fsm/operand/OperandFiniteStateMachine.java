package com.teamdev.calculator.compiler.fsm.operand;

import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.expression.NumberState;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Arrays;

public class OperandFiniteStateMachine extends FiniteStateMachine<ShuntingYardStack> {

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(OperandFiniteStateMachine.class);
    public OperandFiniteStateMachine(){

        logger.info("Create Operand FSM");
        NumberState numberState = new NumberState();
        OpenBracketState openBracket = new OpenBracketState();
        ExpressionState expressionState = new ExpressionState();
        CloseBracketState closeBracket = new CloseBracketState();
        FunctionState functionState = new FunctionState();

        openBracket.addTransition(expressionState);
        expressionState.addTransition(closeBracket);

        setStartStates(Arrays.asList(numberState, openBracket, functionState));
        setFinishStates(Arrays.asList(numberState, closeBracket, functionState));
    }
}
