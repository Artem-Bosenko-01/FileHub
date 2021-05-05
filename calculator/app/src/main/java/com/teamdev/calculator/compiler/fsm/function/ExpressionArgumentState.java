package com.teamdev.calculator.compiler.fsm.function;

import com.teamdev.calculator.CompilerFactoryImpl;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.FunctionScope;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.List;
import java.util.Optional;

/**
 * This is expression state, that realized by start
 * {@link com.teamdev.calculator.compiler.impl.ExpressionCompiler expression compiler}.
 * It state extends {@link State basic state}
 * It used like {@link FunctionFiniteStateMachine function FSM} state.
 */
public class ExpressionArgumentState extends State<FunctionScope> {
    private final Logger logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(ExpressionArgumentState.class);

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, FunctionScope output) {
        logger.info("Start transition for Argument expression in function");
        CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> factory = new CompilerFactoryImpl();
        ElementCompiler<ShuntingYardStack> expressionElement = factory.create(TypeOfExpressionElement.EXPRESSION);
        Optional<Command<ShuntingYardStack>> command = expressionElement.compile(characterStream);
        ShuntingYardStack stack = new ShuntingYardStack();

        if (command.isPresent()) {
            command.get().execute(stack);
            output.addArgument(stack.calculate());
            logger.info("transition successful");
            return true;
        }
        return false;
    }

}
