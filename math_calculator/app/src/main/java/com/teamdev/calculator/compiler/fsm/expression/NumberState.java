package com.teamdev.calculator.compiler.fsm.expression;

import com.teamdev.calculator.CompilerFactoryImpl;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Optional;


public class NumberState extends State<ShuntingYardStack> {

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(NumberState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack stack) {

        logger.info("Try transition for number state");
        CompilerFactory factory = new CompilerFactoryImpl();
        Optional<Command> command = factory.create(TypeOfExpressionElement.NUMBER).compile(characterStream);
        if(command.isPresent()) {
            command.get().execute(stack);
            logger.info("Transition successful");
            return true;
        }

        return false;
    }
}
