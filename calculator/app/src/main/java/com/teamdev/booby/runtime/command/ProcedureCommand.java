package com.teamdev.booby.runtime.command;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.FunctionCommand;
import com.teamdev.calculator.runtime.FunctionScope;

public class ProcedureCommand implements Command<RuntimeEnvironment> {
    private final FunctionScope scope;

    public ProcedureCommand(FunctionScope scope) {
        this.scope = scope;
    }

    @Override
    public void execute(RuntimeEnvironment stack) {
        scope.getFunction().apply(scope.getArguments());
    }
}
