package com.teamdev.calculator.compiler.fsm.exception;

public class InvalidBinaryOperatorException extends Exception{
    public InvalidBinaryOperatorException(String symbol){
        super("You input invalid binary operator on "+ symbol+" position");
    }
}
