package com.teamdev.calculator.compiler.impl;

import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.number.NumberFiniteStateMachine;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.OperandCommand;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Optional;

public class NumberCompiler implements ElementCompiler<ShuntingYardStack> {
    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(NumberCompiler.class);

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        logger.info("Start compile Number Compiler");
        NumberFiniteStateMachine machine = new NumberFiniteStateMachine();
        StringBuilder output = new StringBuilder();
        try {
            if(machine.execute(stream, output)){
                return Optional.of(new OperandCommand(Double.parseDouble(output.toString())));
            }

        } catch (NumberFormatException e) {
            e.getMessage();
        }
        return Optional.empty();
    }
}
