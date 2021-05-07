package com.teamdev.calculator.compiler.booleanexpression;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.runtime.operators.BooleanScope;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;

import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class BooleanExpressionCompiler implements ElementCompiler<ShuntingYardStack> {
    private final CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory;

    public BooleanExpressionCompiler(CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        BooleanExpressionFiniteStateMachine machine = new BooleanExpressionFiniteStateMachine(compilerFactory);
        BooleanScope scope = new BooleanScope();
        machine.execute(stream, scope);
        return Optional.empty();
    }
}
