package com.teamdev.booby.compiler.fsm.program;

import com.teamdev.booby.compiler.fsm.statement.StatementCompiler;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.Command;

import java.util.Optional;

public class StatementState extends State<RuntimeEnvironment> {

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, RuntimeEnvironment builder) {

        Optional<Command<RuntimeEnvironment>> command = new StatementCompiler().compile(characterStream);
        return !command.isPresent();
    }
}
