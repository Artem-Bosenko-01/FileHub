package com.teamdev.booby.compiler.statement;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * This is machine, that processes one of states: {@link InitVariableState variable} or
 * {@link ProcedureState procedure}.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class StatementFiniteStateMachine extends FiniteStateMachine<RuntimeEnvironment> {
    private final InitVariableState initVariableState;
    private final ProcedureState procedureState;
    private final Logger logger = LoggerFactory.getLogger(StatementFiniteStateMachine.class);

    public StatementFiniteStateMachine(CompilerFactory<RuntimeEnvironment> compiler){
        logger.info("Start Program FSM");
        initVariableState = new InitVariableState(compiler);
        procedureState = new ProcedureState(compiler);
    }

    @Override
    protected List<State<RuntimeEnvironment>> getStartStates() {
        return Arrays.asList(initVariableState, procedureState);
    }

    @Override
    protected List<State<RuntimeEnvironment>> getFinishStates() {
        return Arrays.asList(initVariableState, procedureState);
    }
}
