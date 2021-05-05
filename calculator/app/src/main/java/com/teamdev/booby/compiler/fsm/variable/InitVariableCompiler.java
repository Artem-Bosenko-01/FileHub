package com.teamdev.booby.compiler.fsm.variable;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.booby.runtime.command.InitVariableResultCommand;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.holder.booleantype.BooleanValueHolder;
import com.teamdev.calculator.runtime.holder.doubletype.DoubleValueHolder;

import java.util.Optional;

public class InitVariableCompiler implements ElementCompiler<RuntimeEnvironment> {
    @Override
    public Optional<Command<RuntimeEnvironment>> compile(InputCharacterStream stream) {
        InitVariableFiniteStateMachine varFiniteStateMachine = new InitVariableFiniteStateMachine();
        StringBuilder builder = new StringBuilder();
        try {
            if(varFiniteStateMachine.execute(stream,builder)){
                String[] components = builder.toString().split("=");
                if(components[1].matches("[0-9]+(.[0-9][0-9]?)?")){
                    return Optional.of(new InitVariableResultCommand(components[0], new DoubleValueHolder(Double.parseDouble(components[1]))));
                }
                else return Optional.of(new InitVariableResultCommand(components[0], new BooleanValueHolder(Boolean.valueOf(components[1]))));

            }

        } catch (InvalidSymbolException | NotExistPairBracketException e) {
            e.printStackTrace();
        }
       return Optional.empty();
    }
}
