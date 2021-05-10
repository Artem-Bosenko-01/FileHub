package com.teamdev.calculator.compiler.doubleexpression;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.command.OperatorCommand;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 *This state compile {@link OperatorCommand operator command} for {@link ShuntingYardStack stack}
 * in {@link ExpressionFiniteStateMachine expression FSM} if input symbol equals to one of {@link #matcher operator}.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class OperatorState extends State<ShuntingYardStack> {

    private static final Pattern matcher = Pattern.compile("[-*/^+]");
    private final Logger logger = LoggerFactory.getLogger(OperatorState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack outputChain) {
        logger.info("Try transition for operator state");
        if(matcher.matcher(String.valueOf(characterStream.getCurrentSymbol())).matches()){
            Command<ShuntingYardStack> command = new OperatorCommand(String.valueOf(characterStream.getCurrentSymbol()));
            command.execute(outputChain);
            characterStream.increasePointer();
            logger.info("Transition OperatorState successful");
            return true;
        }
        return false;
    }
}
