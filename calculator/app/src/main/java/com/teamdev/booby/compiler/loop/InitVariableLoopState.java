package com.teamdev.booby.compiler.loop;

import com.teamdev.booby.compiler.variable.InitVariableCompiler;
import com.teamdev.booby.runtime.ForOutputChain;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;

import java.util.Optional;

public class InitVariableLoopState extends State<ForOutputChain> {

    private final CompilerFactory<ShuntingYardStack> compilerFactory;

    public InitVariableLoopState(CompilerFactory<ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ForOutputChain builder) {
        Optional<Command<RuntimeEnvironment>> command = new InitVariableCompiler(compilerFactory).compile(characterStream);

        if(command.isPresent()){
            RuntimeEnvironment environment =RuntimeEnvironment.getInstance();
            command.get().execute(environment);
            builder.setInitLoopCommand(command.get());
            return true;
        }
        return false;
    }
}
