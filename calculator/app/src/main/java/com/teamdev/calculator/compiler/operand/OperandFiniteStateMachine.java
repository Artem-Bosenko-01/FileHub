package com.teamdev.calculator.compiler.operand;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.doubleexpression.ExpressionFiniteStateMachine;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 *This is {@link FiniteStateMachine machine}, that used for detect
 * {@link ExpressionFiniteStateMachine expression} with brackets,
 * {@link com.teamdev.calculator.compiler.number.NumberFiniteStateMachine number} or
 * {@link com.teamdev.calculator.compiler.function.FunctionFiniteStateMachine function} values in
 * {@link com.teamdev.calculator.compiler.InputCharacterStream input expression}
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyTransitiveDependencies", "ClassWithTooManyDependencies"})
public class OperandFiniteStateMachine extends FiniteStateMachine<ShuntingYardStack> {

    private final NumberState numberState;
    private final OpenBracketState<ShuntingYardStack> openBracket = new OpenBracketState<>();
    private final CloseBracketState<ShuntingYardStack> closeBracket = new CloseBracketState<>();
    private final SymbolState symbolState = new SymbolState();
    private final FunctionState functionState;


    public OperandFiniteStateMachine(CompilerFactory<ShuntingYardStack> compilerFactory){
        Logger logger = LoggerFactory.getLogger(OperandFiniteStateMachine.class);
        logger.info("Create Operand FSM");

        ExpressionState expressionState = new ExpressionState(compilerFactory);
        functionState = new FunctionState(compilerFactory);
        numberState= new NumberState(compilerFactory);
        openBracket.addTransition(expressionState);
        expressionState.addTransition(closeBracket);

    }

    @Override
    public List<State<ShuntingYardStack>> getStartStates() {
        return Arrays.asList(numberState, openBracket, functionState, symbolState);
    }

    @Override
    public List<State<ShuntingYardStack>> getFinishStates() {
        return Arrays.asList(numberState, closeBracket, functionState, symbolState);
    }
}
