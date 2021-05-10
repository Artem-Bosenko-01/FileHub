package com.teamdev.booby.impl;

import com.teamdev.booby.Booby;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.Calculator;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.impl.CompilerFactoryImpl;
import com.teamdev.calculator.runtime.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * This is implementation for {@link Booby booby compiler}.
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class BoobyImpl implements Booby {

    private final CompilerFactory<RuntimeEnvironment> compiler;
    private final Logger logger = LoggerFactory.getLogger(BoobyImpl.class);

    public BoobyImpl(CompilerFactory<RuntimeEnvironment> compiler) {
        this.compiler = compiler;
    }

    @Override
    public void execute(String program, RuntimeEnvironment environment) {

        logger.info("Start execute booby compiler for" + program);
        InputCharacterStream stream = new InputCharacterStream(program);
        Optional<Command<RuntimeEnvironment>> command = compiler
                .create(TypeOfExpressionElement.PROGRAM)
                .compile(stream);

        command.ifPresent(resultScopeCommand -> resultScopeCommand.execute(environment));

    }
}
