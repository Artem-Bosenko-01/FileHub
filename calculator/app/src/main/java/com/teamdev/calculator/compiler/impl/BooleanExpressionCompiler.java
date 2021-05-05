package com.teamdev.calculator.compiler.impl;

import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.booleanexpression.BooleanExpressionFiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.runtime.BooleanScope;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;

import java.util.Optional;

public class BooleanExpressionCompiler implements ElementCompiler<ShuntingYardStack> {

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        BooleanExpressionFiniteStateMachine machine = new BooleanExpressionFiniteStateMachine();
        BooleanScope scope = new BooleanScope();
        try {
            machine.execute(stream, scope);
        } catch (InvalidSymbolException | NotExistPairBracketException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
