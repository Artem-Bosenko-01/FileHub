package com.teamdev.calculator.compiler.fsm.operand;

import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 *This is {@link FiniteStateMachine machine}, that used for detect
 * {@link com.teamdev.calculator.compiler.fsm.ExpressionFiniteStateMachine expression} with brackets,
 * {@link com.teamdev.calculator.compiler.fsm.number.NumberFiniteStateMachine number} or
 * {@link com.teamdev.calculator.compiler.fsm.function.FunctionFiniteStateMachine function} values in
 * {@link com.teamdev.calculator.compiler.InputCharacterStream input expression}
 * */
public class OperandFiniteStateMachine extends FiniteStateMachine<ShuntingYardStack> {

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(OperandFiniteStateMachine.class);
    private final NumberState numberState = new NumberState();
    private final OpenBracketState<ShuntingYardStack> openBracket = new OpenBracketState<>();
    private final ExpressionState expressionState = new ExpressionState();
    private final CloseBracketState<ShuntingYardStack> closeBracket = new CloseBracketState<>();
    private final FunctionState functionState = new FunctionState();

    public OperandFiniteStateMachine(){

        logger.info("Create Operand FSM");

        openBracket.addTransition(expressionState);
        expressionState.addTransition(closeBracket);

    }

    @Override
    public List<State<ShuntingYardStack>> getStartStates() {
        return Arrays.asList(numberState, openBracket, functionState);
    }

    @Override
    public List<State<ShuntingYardStack>> getFinishStates() {
        return Arrays.asList(numberState, closeBracket, functionState);
    }
}
