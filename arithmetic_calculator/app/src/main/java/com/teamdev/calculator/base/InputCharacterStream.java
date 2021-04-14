package com.teamdev.calculator.base;

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

    public void increasePointer() {
        pointer++;
    }

    public int getPointer(){return pointer;}
    public boolean isEmpty() {
        return pointer >= sentence.length();
    }

}
