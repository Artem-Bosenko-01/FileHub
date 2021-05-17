package com.teamdev.booby.compiler.program;

import com.teamdev.booby.compiler.procedure.ProcedureCompiler;
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
 * This is compiler, that processes {@link ProgramFiniteStateMachine program FSM}.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class ProgramCompiler implements ElementCompiler<RuntimeEnvironment> {

    private final CompilerFactory<RuntimeEnvironment> compiler;
    private final Logger logger = LoggerFactory.getLogger(ProcedureCompiler.class);

    public ProgramCompiler(CompilerFactory<RuntimeEnvironment> compiler) {
        this.compiler = compiler;
    }

    @Override
    public Optional<Command<RuntimeEnvironment>> compile(InputCharacterStream stream) {

        logger.info("Start compile Program Compiler");
        ProgramFiniteStateMachine machine = new ProgramFiniteStateMachine(compiler);
        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();

        machine.execute(stream, environment);

        logger.info("Program compiler execute successful");
        return Optional.empty();
    }
}
