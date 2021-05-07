package com.teamdev.booby.compiler.procedure;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.booby.runtime.ProcedureCommand;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.function.FunctionFiniteStateMachine;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.functions.FunctionScope;
import com.teamdev.calculator.runtime.ShuntingYardStack;

import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class ProcedureCompiler implements ElementCompiler<RuntimeEnvironment> {
    private final CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory;

    public ProcedureCompiler(CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public Optional<Command<RuntimeEnvironment>> compile(InputCharacterStream stream) {

        FunctionFiniteStateMachine machine = new FunctionFiniteStateMachine(compilerFactory);
        FunctionScope scope = new FunctionScope();
        if(machine.execute(stream, scope)){
            return Optional.of(new ProcedureCommand(scope));
        }

        return Optional.empty();
    }
}
