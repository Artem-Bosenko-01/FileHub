package com.teamdev.booby.runtime;


import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.functions.FunctionScope;

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
