package com.teamdev.booby.compiler.variable;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.booleanexpression.BooleanExpressionFiniteStateMachine;
import com.teamdev.calculator.runtime.operators.BooleanScope;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.holder.value.DoubleValueHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is one of states for {@link VariableValueFiniteSTateMachine variable value FSM}, that
 * detects and calculate boolean expresion for variable value.
 */
@SuppressWarnings({"ClassWithTooManyTransitiveDependencies", "ClassWithTooManyDependencies"})
public class BooleanExpressionState extends State<ShuntingYardStack> {
    private final CompilerFactory<ShuntingYardStack> compilerFactory;
    private final Logger logger = LoggerFactory.getLogger(BooleanExpressionState.class);

    public BooleanExpressionState(CompilerFactory<ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        logger.info("Try transition for boolean expression state");
        BooleanExpressionFiniteStateMachine machine = new BooleanExpressionFiniteStateMachine(compilerFactory);
        BooleanScope scope = new BooleanScope();
        if(machine.execute(characterStream, scope)){
            builder.pushOperand(scope.getOperator().apply(new DoubleValueHolder(scope.getOperands().get(0)), new DoubleValueHolder(scope.getOperands().get(1))));
            logger.info("Transition BooleanExpressionState successful");
            return true;
        }

        return false;
    }
}
