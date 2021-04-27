package com.teamdev.boobby.compiler.fsm;

import com.teamdev.boobby.runtime.ResultScope;
import com.teamdev.calculator.compiler.fsm.FiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.State;

import java.util.Collections;
import java.util.List;

public class ProgramFiniteStateMachine extends FiniteStateMachine<ResultScope> {
    private final StatementState statementState = new StatementState();
    private final SemicolonState semicolonState = new SemicolonState();
    public ProgramFiniteStateMachine(){



    }

    @Override
    public List<State<ResultScope>> getStartStates() {
        return Collections.singletonList(statementState);
    }

    @Override
    public List<State<ResultScope>> getFinishStates() {
        return Collections.singletonList(semicolonState);
    }
}
