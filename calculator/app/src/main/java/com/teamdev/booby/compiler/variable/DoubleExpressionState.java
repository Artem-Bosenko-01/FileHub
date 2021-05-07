package com.teamdev.booby.compiler.variable;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.doubleexpression.ExpressionFiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;


@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class DoubleExpressionState extends State<ShuntingYardStack> {
    private final CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory;

    public DoubleExpressionState(CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        ExpressionFiniteStateMachine expressionFSM = new ExpressionFiniteStateMachine(compilerFactory);
        return expressionFSM.execute(characterStream, builder);
    }
}
