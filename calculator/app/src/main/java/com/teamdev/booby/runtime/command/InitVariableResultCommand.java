package com.teamdev.booby.runtime.command;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.runtime.Command;

public class InitVariableResultCommand implements Command<RuntimeEnvironment> {

    private final String name;
    private final Double value;

    public InitVariableResultCommand(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.setVariable(name, value);
    }
}
