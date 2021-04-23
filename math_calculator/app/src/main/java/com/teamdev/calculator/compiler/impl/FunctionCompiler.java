package com.teamdev.calculator.compiler.impl;

import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.function.FunctionFiniteStateMachine;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.FunctionCommand;
import com.teamdev.calculator.runtime.FunctionScope;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Optional;

public class FunctionCompiler implements ElementCompiler<ShuntingYardStack> {

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(FunctionCompiler.class);

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        logger.info("Start compile Function Compiler");
        FunctionFiniteStateMachine machine = new FunctionFiniteStateMachine();
        FunctionScope scope = new FunctionScope();
        machine.execute(stream, scope);
        return Optional.of(new FunctionCommand(scope.getFunction(), scope.getArguments()));
    }
}
