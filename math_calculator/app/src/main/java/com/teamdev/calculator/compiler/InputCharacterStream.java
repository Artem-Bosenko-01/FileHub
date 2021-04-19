package com.teamdev.calculator.compiler;

public class InputCharacterStream {
    private final String sentence;
    private int pointer;

    public InputCharacterStream(String string) {
        this.sentence = string;
        pointer = 0;
    }

    public char getCurrentSymbol() {
        return sentence.charAt(pointer);
    }

    public char getPreviousSymbol() {
        return sentence.charAt(--pointer);
    }
    public int getSize() {
        return sentence.length();
    }

    public void increasePointer() {
        ++pointer;
    }

    public int getPointer(){return pointer;}
    public boolean isEmpty() {
        return pointer == sentence.length();
    }

}
