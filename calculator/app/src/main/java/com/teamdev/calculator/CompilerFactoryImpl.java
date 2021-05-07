package com.teamdev.calculator;

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

/**
 * This is standard implementation, that return type of {@link ElementCompiler compiler} for appropriate
 * {@link TypeOfExpressionElement type of expression}
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyTransitiveDependencies"})
public class CompilerFactoryImpl implements CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> {
    private static final Logger logger = LoggerFactory.getLogger(CompilerFactoryImpl.class);

    @Override
    public ElementCompiler<ShuntingYardStack> create(TypeOfExpressionElement type) {
        logger.info("Start get instance compiler for type of machine " + type.name());
        switch (type){
            case NUMBER: return new NumberCompiler();
            case EXPRESSION:return new ExpressionCompiler(new CompilerFactoryImpl());
            case FUNCTION: return new FunctionCompiler(new CompilerFactoryImpl());
            case OPERAND: return new OperandCompiler(new CompilerFactoryImpl());
            default: throw new RuntimeException();
        }
    }
}
