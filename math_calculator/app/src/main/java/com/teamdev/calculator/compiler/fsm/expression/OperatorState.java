package com.teamdev.calculator.compiler.fsm.expression;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.OperatorCommand;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.regex.Pattern;

public class OperatorState extends State<ShuntingYardStack> {

    private static final Pattern matcher = Pattern.compile("[-*/^+]");
    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(OperatorState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack outputChain) {
        logger.info("Try transition for operator state");
        if(matcher.matcher(String.valueOf(characterStream.getCurrentSymbol())).matches()){
            Command command = new OperatorCommand(String.valueOf(characterStream.getCurrentSymbol()));
            command.execute(outputChain);
            characterStream.increasePointer();
            logger.info("Transition successful");
            return true;
        }
        return false;
    }
}
