package com.teamdev.booby.compiler.program;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 *This is machine, that processes {@link StatementState statements} through semicolon.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class ProgramFiniteStateMachine extends FiniteStateMachine<RuntimeEnvironment> {

    private final StatementState statementState;
    private final SemicolonState<RuntimeEnvironment> semicolonState;
    private final Logger logger = LoggerFactory.getLogger(ProgramFiniteStateMachine.class);

    public ProgramFiniteStateMachine(CompilerFactory<RuntimeEnvironment> compiler){

        logger.info("Start Program FSM");
        statementState = new StatementState(compiler);
        semicolonState = new SemicolonState<>();

        statementState.addTransition(semicolonState);
        semicolonState.addTransition(statementState);
    }


    @Override
    public List<State<RuntimeEnvironment>> getStartStates() {
        return Collections.singletonList(statementState);
    }

    @Override
    public List<State<RuntimeEnvironment>> getFinishStates() {
        return Collections.singletonList(semicolonState);
    }
}
