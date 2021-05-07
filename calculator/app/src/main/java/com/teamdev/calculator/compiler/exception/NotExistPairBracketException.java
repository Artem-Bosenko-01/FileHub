package com.teamdev.calculator.compiler.exception;

@SuppressWarnings("ClassOnlyUsedInOnePackage")
public class NotExistPairBracketException extends Exception{

    private static final long serialVersionUID = 1310734509655788859L;

    public NotExistPairBracketException(){
        super("Doesn't exist pair for bracket");
    }
}
