package com.teamdev.calculator.compiler.impl;

import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.ExpressionFiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.OperandCommand;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.holder.doubletype.DoubleVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Optional;

public class ExpressionCompiler implements ElementCompiler<ShuntingYardStack> {

    private final Logger logger = LoggerFactory.getLogger(ExpressionCompiler.class);

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        logger.info("Start compile Expression Compiler");
        ExpressionFiniteStateMachine machine = new ExpressionFiniteStateMachine();
        ShuntingYardStack stack = new ShuntingYardStack();
        try {
            machine.execute(stream, stack);
        } catch (NumberFormatException | NotExistPairBracketException | InvalidSymbolException e) {
            logger.error(e.getMessage());
        }

        return Optional.of(new OperandCommand(stack.calculate()));

    }
}
