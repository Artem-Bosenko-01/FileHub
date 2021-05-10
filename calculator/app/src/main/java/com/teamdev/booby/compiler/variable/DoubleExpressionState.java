package com.teamdev.booby.compiler.variable;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.doubleexpression.ExpressionFiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is one of states for {@link VariableValueFiniteSTateMachine variable value FSM}, that
 * detects and calculate double math expresion for variable value.
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class DoubleExpressionState extends State<ShuntingYardStack> {

    private final CompilerFactory<ShuntingYardStack> compilerFactory;
    private final Logger logger = LoggerFactory.getLogger(DoubleExpressionState.class);

    public DoubleExpressionState(CompilerFactory<ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        logger.info("Try transition for double math expression state");
        ExpressionFiniteStateMachine expressionFSM = new ExpressionFiniteStateMachine(compilerFactory);
        if(expressionFSM.execute(characterStream, builder)){
            logger.info("Transition DoubleExpressionState successful");
            return true;
        }
        return false;
    }
}
