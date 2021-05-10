package com.teamdev.booby.compiler.statement;

import com.teamdev.booby.compiler.loop.ForLoopCompiler;
import com.teamdev.booby.runtime.ForOutputChain;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.impl.CompilerFactoryImpl;
import com.teamdev.calculator.runtime.command.Command;

import java.util.Optional;

public class ForLoopState extends State<RuntimeEnvironment> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, RuntimeEnvironment builder) {

        Optional<Command<RuntimeEnvironment>> command = new ForLoopCompiler(new CompilerFactoryImpl()).compile(characterStream);
        if(command.isPresent()){
            command.get().execute(builder);
            return true;
        }
        return false;
    }
}
