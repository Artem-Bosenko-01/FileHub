package com.teamdev.booby.impl;

import com.teamdev.booby.Booby;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.booby.compiler.TypeBoobyExpression;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.command.Command;

import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class BoobyImpl implements Booby {

    private final CompilerFactory<TypeBoobyExpression, RuntimeEnvironment> compiler;

    public BoobyImpl(CompilerFactory<TypeBoobyExpression, RuntimeEnvironment> compiler) {
        this.compiler = compiler;
    }

    @Override
    public void execute(String program, RuntimeEnvironment environment) {
        InputCharacterStream stream = new InputCharacterStream(program);
        Optional<Command<RuntimeEnvironment>> command = compiler
                .create(TypeBoobyExpression.PROGRAM)
                .compile(stream);

        command.ifPresent(resultScopeCommand -> resultScopeCommand.execute(environment));

    }
}
