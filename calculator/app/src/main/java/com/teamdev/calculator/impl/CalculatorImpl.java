package com.teamdev.calculator.impl;

import com.teamdev.calculator.Calculator;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.Optional;

import static com.teamdev.calculator.compiler.TypeOfExpressionElement.EXPRESSION;

/**
 * This is implementation for {@link Calculator calculator} of {@link Calculator calculator API}.
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class CalculatorImpl implements Calculator {
    private final Logger logger = LoggerFactory.getLogger(CompilerFactoryImpl.class);
    private final StringBuilder writer;

    public CalculatorImpl(StringBuilder writer) {
        this.writer = writer;
    }

    @Override
    public Optional<Double> calculate(String inputChain) {
        logger.info("Start calculate operation for" + inputChain);
        InputCharacterStream characterStream = new InputCharacterStream(inputChain);
        ElementCompiler<ShuntingYardStack> compiler = new CompilerFactoryImpl(writer).create(EXPRESSION);
        ShuntingYardStack stack = new ShuntingYardStack();

        Optional<Command<ShuntingYardStack>> command = compiler.compile(characterStream);

        command.ifPresent(value -> value.execute(stack));
        return Optional.of((Double) stack.calculate().getValue());

    }
}
