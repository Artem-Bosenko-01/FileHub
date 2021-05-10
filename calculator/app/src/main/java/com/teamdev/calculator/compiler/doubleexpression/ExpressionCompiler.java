package com.teamdev.calculator.compiler.doubleexpression;

import com.teamdev.calculator.impl.CompilerFactoryImpl;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.command.OperandCommand;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyTransitiveDependencies"})
public class ExpressionCompiler implements ElementCompiler<ShuntingYardStack> {

    private final Logger logger = LoggerFactory.getLogger(ExpressionCompiler.class);
    private final CompilerFactory<ShuntingYardStack> compilerFactory;

    public ExpressionCompiler(CompilerFactoryImpl compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        logger.info("Start compile Expression Compiler");
        ExpressionFiniteStateMachine machine = new ExpressionFiniteStateMachine(compilerFactory);
        ShuntingYardStack stack = new ShuntingYardStack();
        try {
            machine.execute(stream, stack);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }

        return Optional.of(new OperandCommand(stack.calculate()));

    }
}
