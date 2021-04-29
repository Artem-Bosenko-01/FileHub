package com.teamdev.booby.impl;

import com.teamdev.booby.Booby;
import com.teamdev.booby.BoobyCompilerFactoryImpl;
import com.teamdev.booby.RuntimeEnvironment;
import com.teamdev.booby.compiler.TypeBoobyExpression;
import com.teamdev.booby.runtime.ResultScope;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.Command;

import java.util.Optional;

public class BoobyImpl implements Booby {
    @Override
    public void execute(String program, RuntimeEnvironment environment) {
        InputCharacterStream stream = new InputCharacterStream(program);
        ResultScope scope = new ResultScope();
        Optional<Command<ResultScope>> command = new BoobyCompilerFactoryImpl().create(TypeBoobyExpression.PROGRAM).compile(stream);

        command.ifPresent(resultScopeCommand -> resultScopeCommand.execute(scope));
    }
}
