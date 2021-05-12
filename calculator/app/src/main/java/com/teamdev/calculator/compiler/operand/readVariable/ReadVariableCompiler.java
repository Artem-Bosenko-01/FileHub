package com.teamdev.calculator.compiler.operand.readVariable;

import com.teamdev.booby.compiler.procedure.ProcedureCompiler;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * This is compiler, that processes {@link ReadVariableFSM read variable FSM} and push new
 * operand in {@link ShuntingYardStack}.
 * */
public class ReadVariableCompiler implements ElementCompiler<ShuntingYardStack> {
    private final Logger logger = LoggerFactory.getLogger(ReadVariableCompiler.class);

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {

        logger.info("Start compile Read variable Compiler");
        ReadVariableFSM machine = new ReadVariableFSM();
        ShuntingYardStack mainStack = new ShuntingYardStack();
        if(machine.execute(stream, mainStack)){
            logger.info("Read variable compiler execute successful");
            return Optional.of(stack -> stack.pushOperand(mainStack.calculate()));
        }

        return Optional.empty();
    }
}
