package com.teamdev.calculator.impl;

import com.teamdev.calculator.Calculator;
import com.teamdev.calculator.CompilerFactoryImpl;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Optional;

import static com.teamdev.calculator.compiler.TypeOfExpressionElement.EXPRESSION;

/**
 * This is standard implementation of {@link Calculator calculator API}
 * */
public class CalculatorImpl implements Calculator {
    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(CompilerFactoryImpl.class);

    @Override
    public double calculate(String inputChain) {
        logger.info("Start calculate operation for" + inputChain);
        InputCharacterStream characterStream = new InputCharacterStream(inputChain);
        CompilerFactory compilerFactory = new CompilerFactoryImpl();
        ShuntingYardStack stack = new ShuntingYardStack();

        ElementCompiler<ShuntingYardStack> compiler = compilerFactory.create(EXPRESSION);
        Optional<Command<ShuntingYardStack>> command = compiler.compile(characterStream);

        command.ifPresent(value -> value.execute(stack));
        return stack.calculate();
    }
}
