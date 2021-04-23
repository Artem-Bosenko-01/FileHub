package com.teamdev.calculator;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.impl.ExpressionCompiler;
import com.teamdev.calculator.compiler.impl.FunctionCompiler;
import com.teamdev.calculator.compiler.impl.NumberCompiler;

import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

public class CompilerFactoryImpl implements CompilerFactory {
    private static Log4jLoggerAdapter loggger = (Log4jLoggerAdapter) LoggerFactory.getLogger(CompilerFactoryImpl.class);


    @Override
    public ElementCompiler create(TypeOfExpressionElement type) {
        loggger.info("Start get instance compiler for type of machine " + type.name());
        switch (type){
            case NUMBER: return new NumberCompiler();
            case EXPRESSION:return new ExpressionCompiler();
            case FUNCTION: return new FunctionCompiler();
            default: throw new RuntimeException();
        }
    }
}
