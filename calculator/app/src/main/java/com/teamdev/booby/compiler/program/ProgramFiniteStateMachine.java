package com.teamdev.booby.compiler.program;

import com.teamdev.booby.compiler.TypeBoobyExpression;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class ProgramFiniteStateMachine extends FiniteStateMachine<RuntimeEnvironment> {

    private final StatementState statementState;
    private final SemicolonState semicolonState;

    public ProgramFiniteStateMachine(CompilerFactory<TypeBoobyExpression, RuntimeEnvironment> compiler){
        statementState = new StatementState(compiler);
        semicolonState = new SemicolonState();

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
