package com.teamdev.calculator.compiler.impl;

import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.ExpressionFiniteStateMachine;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.OperandCommand;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Optional;

public class ExpressionCompiler implements ElementCompiler<ShuntingYardStack> {

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(ExpressionCompiler.class);

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        logger.info("Start compile Expression Compiler");
        ExpressionFiniteStateMachine machine = new ExpressionFiniteStateMachine();
        ShuntingYardStack stack = new ShuntingYardStack();
        try {
            machine.execute(stream, stack);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }
        return Optional.of(new OperandCommand(stack.calculate()));
    }
}
