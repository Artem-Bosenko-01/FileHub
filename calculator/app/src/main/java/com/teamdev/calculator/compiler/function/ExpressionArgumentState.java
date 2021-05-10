package com.teamdev.calculator.compiler.function;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.doubleexpression.ExpressionCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.functions.FunctionScope;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * This is expression state, that realized by start
 * {@link ExpressionCompiler expression compiler}.
 * It state extends {@link State basic state}
 * It used like {@link FunctionFiniteStateMachine function FSM} state.
 */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyTransitiveDependencies"})
public class ExpressionArgumentState extends State<FunctionScope> {
    private final Logger logger = LoggerFactory.getLogger(ExpressionArgumentState.class);
    private final CompilerFactory<ShuntingYardStack> compilerFactory;

    public ExpressionArgumentState(CompilerFactory<ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, FunctionScope output) {
        logger.info("Start transition for Argument expression in function");
        Optional<Command<ShuntingYardStack>> command = compilerFactory
                .create(TypeOfExpressionElement.EXPRESSION)
                .compile(characterStream);
        ShuntingYardStack stack = new ShuntingYardStack();

        if (command.isPresent()) {
            command.get().execute(stack);
            output.addArgument(stack.calculate());
            logger.info("transition ExpressionArgumentState successful");
            return true;
        }
        return false;
    }

}
