package com.teamdev.booby.compiler.loop;

import com.teamdev.booby.compiler.program.ProgramCompiler;
import com.teamdev.booby.impl.BoobyCompilerFactoryImpl;
import com.teamdev.booby.runtime.ForOutputChain;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.command.Command;

import java.util.Optional;

public class CodeBlockState extends State<ForOutputChain > {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ForOutputChain builder) {

        Optional<Command<RuntimeEnvironment>> command = new ProgramCompiler(new BoobyCompilerFactoryImpl()).compile(characterStream);
        builder.setCodeBlockCommands(command.get());
        return true;
    }
}
