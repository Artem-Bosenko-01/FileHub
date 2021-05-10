package com.teamdev.booby.compiler.loop;

import com.teamdev.booby.runtime.ForExecuteCommand;
import com.teamdev.booby.runtime.ForOutputChain;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;

import java.util.Optional;

public class ForLoopCompiler implements ElementCompiler<RuntimeEnvironment> {

    private final CompilerFactory<ShuntingYardStack> compilerFactory;

    public ForLoopCompiler(CompilerFactory<ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public Optional<Command<RuntimeEnvironment>> compile(InputCharacterStream stream) {
        ForLoopFiniteStateMachine machine = new ForLoopFiniteStateMachine(compilerFactory);
        ForOutputChain outputChain = new ForOutputChain();
        if(machine.execute(stream, outputChain)){
            return Optional.of(new ForExecuteCommand(outputChain));
        }
        return Optional.empty();
    }
}
