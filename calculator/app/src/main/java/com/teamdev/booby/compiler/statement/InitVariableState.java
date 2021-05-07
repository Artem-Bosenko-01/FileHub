package com.teamdev.booby.compiler.statement;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.booby.compiler.TypeBoobyExpression;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.command.Command;

import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class InitVariableState extends State<RuntimeEnvironment> {
    private final CompilerFactory<TypeBoobyExpression, RuntimeEnvironment> compiler;

    public InitVariableState(CompilerFactory<TypeBoobyExpression, RuntimeEnvironment> compiler) {
        this.compiler = compiler;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, RuntimeEnvironment builder) {
        Optional<Command<RuntimeEnvironment>> command = compiler
                .create(TypeBoobyExpression.VARIABLE)
                .compile(characterStream);

        if (command.isPresent()) {
            command.get().execute(builder);
            return true;
        }
        return false;
    }
}
