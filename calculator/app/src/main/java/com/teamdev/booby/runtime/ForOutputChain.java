package com.teamdev.booby.runtime;

import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;

public class ForOutputChain {

    private Command<RuntimeEnvironment> initLoopCommand;
    private Command<ShuntingYardStack> loopCondition;
    private Command<RuntimeEnvironment> iterationAction;
    private Command<RuntimeEnvironment> codeBlockCommands;

    public Command<RuntimeEnvironment> getInitLoopCommand() {
        return initLoopCommand;
    }

    public Command<ShuntingYardStack> getLoopCondition() {
        return loopCondition;
    }

    public Command<RuntimeEnvironment> getIterationAction() {
        return iterationAction;
    }

    public Command<RuntimeEnvironment> getCodeBlockCommands() {
        return codeBlockCommands;
    }

    public void setInitLoopCommand(Command<RuntimeEnvironment> initLoopCommand) {
        this.initLoopCommand = initLoopCommand;
    }

    public void setLoopCondition(Command<ShuntingYardStack> loopCondition) {
        this.loopCondition = loopCondition;
    }

    public void setIterationAction(Command<RuntimeEnvironment> iterationAction) {
        this.iterationAction = iterationAction;
    }

    public void setCodeBlockCommands(Command<RuntimeEnvironment> codeBlockCommands) {
        this.codeBlockCommands = codeBlockCommands;
    }
}
