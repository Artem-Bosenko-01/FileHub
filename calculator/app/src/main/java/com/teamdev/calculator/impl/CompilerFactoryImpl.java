package com.teamdev.calculator.impl;

import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.doubleexpression.ExpressionCompiler;
import com.teamdev.calculator.compiler.function.FunctionCompiler;
import com.teamdev.calculator.compiler.number.NumberCompiler;
import com.teamdev.calculator.compiler.operand.OperandCompiler;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

/**
 * This is implementation compiler factory for {@link com.teamdev.calculator.Calculator},
 * that return type of {@link ElementCompiler compiler} for appropriate
 * {@link TypeOfExpressionElement type of expression}.
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyTransitiveDependencies"})
public class CompilerFactoryImpl implements CompilerFactory<ShuntingYardStack> {
    private static final Logger logger = LoggerFactory.getLogger(CompilerFactoryImpl.class);
    private final StringBuilder writer;

    public CompilerFactoryImpl(StringBuilder writer) {
        this.writer = writer;
    }

    @Override
    public ElementCompiler<ShuntingYardStack> create(TypeOfExpressionElement type) {
        logger.info("Start get instance compiler for type of machine " + type.name());
        switch (type){
            case NUMBER: return new NumberCompiler();
            case EXPRESSION:return new ExpressionCompiler(new CompilerFactoryImpl(writer));
            case FUNCTION: return new FunctionCompiler(new CompilerFactoryImpl(writer), writer);
            case OPERAND: return new OperandCompiler(new CompilerFactoryImpl(writer));
            default: throw new RuntimeException();
        }
    }
}
