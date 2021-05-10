package com.teamdev.booby.runtime;

import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.holder.value.BooleanVisitor;

public class ForExecuteCommand implements Command<RuntimeEnvironment> {

    private final ForOutputChain forOutputChain;

    public ForExecuteCommand(ForOutputChain forOutputChain) {
        this.forOutputChain = forOutputChain;
    }

    @Override
    public void execute(RuntimeEnvironment stack) {
        forOutputChain.getInitLoopCommand().execute(stack);

        ShuntingYardStack shuntingYardStack = new ShuntingYardStack();
        forOutputChain.getLoopCondition().execute(shuntingYardStack);
        BooleanVisitor visitor = new BooleanVisitor();

        while (visitor.getBooleanValue(shuntingYardStack.calculate()).get()){
            forOutputChain.getCodeBlockCommands().execute(stack);
            forOutputChain.getIterationAction().execute(stack);
        }
    }
}
