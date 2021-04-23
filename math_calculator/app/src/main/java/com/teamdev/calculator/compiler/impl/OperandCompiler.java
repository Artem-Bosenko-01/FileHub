package com.teamdev.calculator.compiler.impl;

import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.operand.OperandFiniteStateMachine;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.OperandFSMCommand;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.IllegalFormatException;
import java.util.Optional;

public class OperandCompiler implements ElementCompiler<ShuntingYardStack> {

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(OperandCompiler.class);
    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        logger.info("Start compile Operand Compiler");
        OperandFiniteStateMachine machine = new OperandFiniteStateMachine();
        ShuntingYardStack stack = new ShuntingYardStack();
        try {
            if(machine.execute(stream, stack)){
                return Optional.of(new OperandFSMCommand(stack));
            }
        } catch (IllegalFormatException e) {
            e.getMessage();
        }
        return Optional.empty();
    }
}
