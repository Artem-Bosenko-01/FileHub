package com.teamdev.booby.compiler.statement;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.Optional;

/**
 * This is compiler, that processes {@link StatementFiniteStateMachine statement FSM}.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class StatementCompiler implements ElementCompiler<RuntimeEnvironment> {
    private final CompilerFactory<RuntimeEnvironment> compiler;
    private final Logger logger = LoggerFactory.getLogger(StatementCompiler.class);

    public StatementCompiler(CompilerFactory<RuntimeEnvironment> compiler) {
        this.compiler = compiler;
    }

    @Override
    public Optional<Command<RuntimeEnvironment>> compile(InputCharacterStream stream) {
        logger.info("Start compile Statement Compiler");

        StatementFiniteStateMachine machine = new StatementFiniteStateMachine(compiler);
        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();
        machine.execute(stream,environment);

        logger.info("Statement compiler execute successful");
        return Optional.empty();
    }
}
