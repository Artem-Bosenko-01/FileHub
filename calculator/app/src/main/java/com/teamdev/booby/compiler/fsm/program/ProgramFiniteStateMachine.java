package com.teamdev.booby.compiler.fsm.program;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.booby.compiler.fsm.statement.InitVariableState;
import com.teamdev.booby.compiler.fsm.statement.ProcedureState;
import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProgramFiniteStateMachine extends FiniteStateMachine<RuntimeEnvironment> {
    private final StatementState statementState = new StatementState();
    private final SemicolonState semicolonState = new SemicolonState();

    public ProgramFiniteStateMachine(){

        statementState.addTransition(semicolonState);
        semicolonState.addTransition(statementState);
    }

    @Override
    public List<State<RuntimeEnvironment>> getStartStates() {
        return Arrays.asList(statementState);
    }

    @Override
    public List<State<RuntimeEnvironment>> getFinishStates() {
        return Collections.singletonList(semicolonState);
    }
}
