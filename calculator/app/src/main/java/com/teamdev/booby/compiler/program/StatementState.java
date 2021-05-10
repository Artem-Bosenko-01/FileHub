package com.teamdev.booby.compiler.program;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.runtime.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Statement state - is one of {@link ProgramFiniteStateMachine Program FSM} state. That generate command
 * from {@link com.teamdev.booby.compiler.statement.StatementCompiler compiler}.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class StatementState extends State<RuntimeEnvironment> {
    private final CompilerFactory<RuntimeEnvironment> compiler;
    private final Logger logger = LoggerFactory.getLogger(StatementState.class);

    public StatementState(CompilerFactory<RuntimeEnvironment> compiler) {
        this.compiler = compiler;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, RuntimeEnvironment builder) {
        logger.info("Start transition for statement state");
        Optional<Command<RuntimeEnvironment>> command = compiler
                .create(TypeOfExpressionElement.STATEMENT)
                .compile(characterStream);
        logger.info("Transition StatementState successful");
        return !command.isPresent();
    }
}
