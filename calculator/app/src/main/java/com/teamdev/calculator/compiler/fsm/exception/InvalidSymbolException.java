package com.teamdev.calculator.compiler.fsm.exception;

public class InvalidSymbolException extends Exception{
    private static final long serialVersionUID = -243625769334325078L;

    public InvalidSymbolException(int position){
        super("You input invalid symbol in " + position);
    }
}
