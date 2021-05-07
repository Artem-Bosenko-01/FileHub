package com.teamdev.calculator.compiler.exception;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class InvalidBinaryOperatorException extends Exception{
    private static final long serialVersionUID = -144098720422818972L;

    public InvalidBinaryOperatorException(String symbol){
        super("You input invalid binary operator on "+ symbol+" position");
    }
}
