package com.teamdev.booby.compiler.statement;

import com.teamdev.booby.compiler.TypeBoobyExpression;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.command.Command;

import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class StatementCompiler implements ElementCompiler<RuntimeEnvironment> {
    private final CompilerFactory<TypeBoobyExpression, RuntimeEnvironment> compiler;

    public StatementCompiler(CompilerFactory<TypeBoobyExpression, RuntimeEnvironment> compiler) {
        this.compiler = compiler;
    }

    @Override
    public Optional<Command<RuntimeEnvironment>> compile(InputCharacterStream stream) {

        StatementFiniteStateMachine machine = new StatementFiniteStateMachine(compiler);
        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();
        machine.execute(stream,environment);
        return Optional.empty();
    }
}
