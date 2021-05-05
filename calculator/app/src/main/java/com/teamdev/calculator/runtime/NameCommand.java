package com.teamdev.calculator.runtime;

import java.util.List;

public class NameCommand implements Command<FunctionScope>{
    private final String name;

    public NameCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(FunctionScope stack) {
        stack.addName(name);
    }
}
