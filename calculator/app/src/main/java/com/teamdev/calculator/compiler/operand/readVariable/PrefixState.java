package com.teamdev.calculator.compiler.operand.readVariable;

import com.teamdev.booby.compiler.unaryoperators.prefix.PrefixOperatorCompiler;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 *This state compile {@link PrefixOperatorCompiler prefix operator compiler}
 * and push command for {@link ShuntingYardStack stack} in {@link ReadVariableFSM read variable FSM}.
 * Also, it puts new variable value in {@link RuntimeEnvironment environment}.
 * */
public class PrefixState extends State<ShuntingYardStack> {
    private final Logger logger = LoggerFactory.getLogger(PrefixState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        logger.info("Try transition for prefix operator state");
        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();
        Optional<Command<ShuntingYardStack>> command = new PrefixOperatorCompiler().compile(characterStream);
        String currentSymbol = String.valueOf(characterStream.getPreviousSymbol());
        characterStream.increasePointer();
        if(command.isPresent()){
            command.get().execute(builder);
            environment.setVariable(currentSymbol,builder.peekOperand().get());
            logger.info("Transition PrefixOperatorState successful");
            return true;
        }

        return false;
    }
}
