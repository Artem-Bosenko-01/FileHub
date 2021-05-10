package com.teamdev.calculator.compiler.booleanexpression;

import com.teamdev.booby.runtime.BooleanExpressionCommand;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.operators.BooleanScope;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;

import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class BooleanExpressionCompiler implements ElementCompiler<ShuntingYardStack> {
    private final CompilerFactory<ShuntingYardStack> compilerFactory;

    public BooleanExpressionCompiler(CompilerFactory<ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        BooleanExpressionFiniteStateMachine machine = new BooleanExpressionFiniteStateMachine(compilerFactory);
        BooleanScope scope = new BooleanScope();
        if(machine.execute(stream, scope)){
            return Optional.of(new BooleanExpressionCommand(scope.getOperands().get(0), scope.getOperator(), scope.getOperands().get(1)));
        }
        return Optional.empty();
    }
}
