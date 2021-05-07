package com.teamdev.booby.compiler.program;

import com.teamdev.booby.compiler.TypeBoobyExpression;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.command.Command;

import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class ProgramCompiler implements ElementCompiler<RuntimeEnvironment> {
    private final CompilerFactory<TypeBoobyExpression, RuntimeEnvironment> compiler;

    public ProgramCompiler(CompilerFactory<TypeBoobyExpression, RuntimeEnvironment> compiler) {
        this.compiler = compiler;
    }

    @Override
    public Optional<Command<RuntimeEnvironment>> compile(InputCharacterStream stream) {

        ProgramFiniteStateMachine machine = new ProgramFiniteStateMachine(compiler);
        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();

        machine.execute(stream, environment);

        return Optional.empty();
    }
}
