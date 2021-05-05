package com.teamdev.booby.runtime.command;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.holder.ValueHolder;

public class InitVariableResultCommand implements Command<RuntimeEnvironment> {

    private final String name;
    private final ValueHolder<?> value;

    public InitVariableResultCommand(String name, ValueHolder<?> value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void execute(RuntimeEnvironment environment) {
        environment.setVariable(name, value);
    }
}
