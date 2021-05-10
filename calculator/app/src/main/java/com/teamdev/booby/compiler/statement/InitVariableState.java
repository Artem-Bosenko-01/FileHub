package com.teamdev.booby.compiler.statement;

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
 * This is one of states for {@link StatementFiniteStateMachine statement FSM}, that defines pair of
 * symbol - value for {@link RuntimeEnvironment environment}.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class InitVariableState extends State<RuntimeEnvironment> {
    private final CompilerFactory<RuntimeEnvironment> compiler;
    private final Logger logger = LoggerFactory.getLogger(InitVariableState.class);

    public InitVariableState(CompilerFactory<RuntimeEnvironment> compiler) {
        this.compiler = compiler;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, RuntimeEnvironment builder) {

        logger.info("Try transition for Init variable state");

        Optional<Command<RuntimeEnvironment>> command = compiler
                .create(TypeOfExpressionElement.VARIABLE)
                .compile(characterStream);

        if (command.isPresent()) {
            command.get().execute(builder);
            logger.info("Transition InitVariableState successful");
            return true;
        }
        return false;
    }
}
