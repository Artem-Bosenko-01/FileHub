package com.teamdev.calculator.compiler.fsm.exception;

public class InvalidSymbolException extends Exception{
    public InvalidSymbolException(int position){
        super("You input invalid symbol in " + position);
    }
}
