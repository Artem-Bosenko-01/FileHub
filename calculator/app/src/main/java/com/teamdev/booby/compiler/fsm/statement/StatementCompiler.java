package com.teamdev.booby.compiler.fsm.statement;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.runtime.Command;

import java.util.Optional;

public class StatementCompiler implements ElementCompiler<RuntimeEnvironment> {

    @Override
    public Optional<Command<RuntimeEnvironment>> compile(InputCharacterStream stream) {

        StatementFiniteStateMachine machine = new StatementFiniteStateMachine();
        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();
        try {
            machine.execute(stream,environment);
        } catch (InvalidSymbolException | NotExistPairBracketException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
