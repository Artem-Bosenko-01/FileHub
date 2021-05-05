package com.teamdev.booby.compiler.fsm.procedure;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.booby.runtime.command.ProcedureCommand;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.compiler.fsm.function.FunctionFiniteStateMachine;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.FunctionCommand;
import com.teamdev.calculator.runtime.FunctionScope;

import java.util.Optional;

public class ProcedureCompiler implements ElementCompiler<RuntimeEnvironment> {
    @Override
    public Optional<Command<RuntimeEnvironment>> compile(InputCharacterStream stream) {

        FunctionFiniteStateMachine machine = new FunctionFiniteStateMachine();
        FunctionScope scope = new FunctionScope();
        try {
            if(machine.execute(stream, scope)){
                return Optional.of(new ProcedureCommand(scope));
            }

        } catch (InvalidSymbolException | NotExistPairBracketException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
