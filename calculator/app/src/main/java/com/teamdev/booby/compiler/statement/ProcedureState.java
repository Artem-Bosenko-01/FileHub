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
 * This is one of states for {@link StatementFiniteStateMachine statement FSM}, that defines and runs
 * procedure.
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class ProcedureState extends State<RuntimeEnvironment> {
    private final CompilerFactory<RuntimeEnvironment> compiler;
    private final Logger logger = LoggerFactory.getLogger(ProcedureState.class);

    public ProcedureState(CompilerFactory<RuntimeEnvironment> compiler) {
        this.compiler = compiler;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, RuntimeEnvironment builder) {

        logger.info("Try transition for Procedure state");

        Optional<Command<RuntimeEnvironment>> command = compiler
                .create(TypeOfExpressionElement.PROCEDURE)
                .compile(characterStream);

        if (command.isPresent()) {
            command.get().execute(builder);
            logger.info("Transition ProcedureState successful");
            return true;
        }
        return false;
    }
}
