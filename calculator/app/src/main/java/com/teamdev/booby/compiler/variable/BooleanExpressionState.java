package com.teamdev.booby.compiler.variable;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.booleanexpression.BooleanExpressionFiniteStateMachine;
import com.teamdev.calculator.runtime.operators.BooleanScope;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.holder.value.DoubleValueHolder;

@SuppressWarnings({"ClassWithTooManyTransitiveDependencies", "ClassWithTooManyDependencies"})
public class BooleanExpressionState extends State<ShuntingYardStack> {
    private final CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory;

    public BooleanExpressionState(CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        BooleanExpressionFiniteStateMachine machine = new BooleanExpressionFiniteStateMachine(compilerFactory);
        BooleanScope scope = new BooleanScope();
        if(machine.execute(characterStream, scope)){
            builder.pushOperand(scope.getOperator().apply(new DoubleValueHolder(scope.getOperands().get(0)), new DoubleValueHolder(scope.getOperands().get(1))));
            return true;
        }

        return false;
    }
}
