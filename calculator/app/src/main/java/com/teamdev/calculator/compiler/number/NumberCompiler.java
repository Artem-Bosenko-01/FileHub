package com.teamdev.calculator.compiler.number;

import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.command.OperandCommand;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.holder.value.DoubleValueHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class NumberCompiler implements ElementCompiler<ShuntingYardStack> {
    private final Logger logger = LoggerFactory.getLogger(NumberCompiler.class);

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        logger.info("Start compile Number Compiler");
        try {

            NumberFiniteStateMachine machine = new NumberFiniteStateMachine();
            StringBuilder output = new StringBuilder(10);
            if(machine.execute(stream, output)){
                return Optional.of(new OperandCommand(new DoubleValueHolder(Double.parseDouble(output.toString()))));
            }

        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }
        return Optional.empty();
    }
}
