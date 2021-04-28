package com.teamdev.calculator.compiler.fsm.exception;

public class NotExistPairBracketException extends Exception{

    public NotExistPairBracketException(){
        super("Doesn't exist pair for bracket");
    }
}
