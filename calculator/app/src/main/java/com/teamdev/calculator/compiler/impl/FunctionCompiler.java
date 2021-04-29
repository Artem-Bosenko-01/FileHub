package com.teamdev.calculator.compiler.impl;

import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.compiler.fsm.function.FunctionFiniteStateMachine;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.FunctionCommand;
import com.teamdev.calculator.runtime.FunctionScope;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Optional;

public class FunctionCompiler implements ElementCompiler<ShuntingYardStack> {

    private final Logger logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(FunctionCompiler.class);

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        logger.info("Start compile Function Compiler");
        FunctionFiniteStateMachine machine = null;
        machine = new FunctionFiniteStateMachine();
        FunctionScope scope = new FunctionScope();
        try {
            machine.execute(stream, scope);
        } catch (InvalidSymbolException | NotExistPairBracketException e) {
            logger.error(e.getMessage());
        }
        return Optional.of(new FunctionCommand(scope.getFunction(), scope.getArguments()));
    }
}
