package com.teamdev.calculator.compiler.operand.readVariable;

import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;

import java.util.Optional;

public class ReadVariableCompiler implements ElementCompiler<ShuntingYardStack> {

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {

        ReadVariableFSM machine = new ReadVariableFSM();
        ShuntingYardStack mainStack = new ShuntingYardStack();
        if(machine.execute(stream, mainStack)){
            return Optional.of(stack -> stack.pushOperand(mainStack.calculate()));
        }

        return Optional.empty();
    }
}
