package com.teamdev.calculator.compiler.operand;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.operand.readVariable.ReadVariableCompiler;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * This state gets value or run unary operator command, if it necessary, for variable name.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class ReadVariableState extends State<ShuntingYardStack> {

    private final Logger logger = LoggerFactory.getLogger(ReadVariableState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        logger.info("Try transition for read variable state");
        Optional<Command<ShuntingYardStack>> command = new ReadVariableCompiler().compile(characterStream);

        if(command.isPresent()){
            command.get().execute(builder);
            logger.info("Transition ReadVariableState successful");
            return true;
        }
        return false;
    }
}
