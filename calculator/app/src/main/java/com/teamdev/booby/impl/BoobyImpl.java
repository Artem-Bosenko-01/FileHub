package com.teamdev.booby.impl;

import com.teamdev.booby.Booby;
import com.teamdev.booby.BoobyCompilerFactoryImpl;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.booby.compiler.TypeBoobyExpression;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.Command;

import java.util.Optional;

public class BoobyImpl implements Booby {
    @Override
    public void execute(String program, RuntimeEnvironment environment) {
        InputCharacterStream stream = new InputCharacterStream(program);
        Optional<Command<RuntimeEnvironment>> command = new BoobyCompilerFactoryImpl()
                .create(TypeBoobyExpression.PROGRAM)
                .compile(stream);

        command.ifPresent(resultScopeCommand -> resultScopeCommand.execute(environment));

    }
}
