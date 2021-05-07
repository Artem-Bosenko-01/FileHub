package com.teamdev.booby.compiler.program;

import com.teamdev.booby.compiler.TypeBoobyExpression;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.command.Command;

import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class StatementState extends State<RuntimeEnvironment> {
    private final CompilerFactory<TypeBoobyExpression, RuntimeEnvironment> compiler;

    public StatementState(CompilerFactory<TypeBoobyExpression, RuntimeEnvironment> compiler) {
        this.compiler = compiler;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, RuntimeEnvironment builder) {

        Optional<Command<RuntimeEnvironment>> command = compiler
                .create(TypeBoobyExpression.STATEMENT)
                .compile(characterStream);
        return !command.isPresent();
    }
}
