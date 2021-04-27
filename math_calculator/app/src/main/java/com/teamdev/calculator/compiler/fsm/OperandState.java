package com.teamdev.calculator.compiler.fsm;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.impl.OperandCompiler;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Optional;

/**
 *This state compile {@link com.teamdev.calculator.compiler.impl.OperandCompiler operand compiler}
 * and push command for {@link ShuntingYardStack stack} in {@link ExpressionFiniteStateMachine expression FSM}
 * */
public class OperandState extends State<ShuntingYardStack> {
    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(OperandState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {

        logger.info("Try transition for operand state");
        OperandCompiler compiler = new OperandCompiler();
        Optional<Command<ShuntingYardStack>> command = compiler.compile(characterStream);

        try {
            if(command.isPresent()){
                command.get().execute(builder);
                logger.info("Transition successful");
                return true;
            }
        } catch (SyntaxException e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}
