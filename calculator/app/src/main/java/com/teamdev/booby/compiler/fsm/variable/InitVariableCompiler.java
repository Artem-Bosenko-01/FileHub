package com.teamdev.booby.compiler.fsm.variable;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.booby.runtime.command.InitVariableResultCommand;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.runtime.Command;

import java.util.Optional;

public class InitVariableCompiler implements ElementCompiler<RuntimeEnvironment> {
    @Override
    public Optional<Command<RuntimeEnvironment>> compile(InputCharacterStream stream) {
        InitVariableFiniteStateMachine varFiniteStateMachine = new InitVariableFiniteStateMachine();
        StringBuilder builder = new StringBuilder();
        try {
            if(varFiniteStateMachine.execute(stream,builder)){
                String[] components = builder.toString().split("=");
                return Optional.of(new InitVariableResultCommand(components[0], Double.parseDouble(components[1])));
            }

        } catch (InvalidSymbolException | NotExistPairBracketException e) {
            e.printStackTrace();
        }
       return Optional.empty();
    }
}
