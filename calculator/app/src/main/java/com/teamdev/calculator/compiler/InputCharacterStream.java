package com.teamdev.calculator.compiler;

/**
 *This is stream, that convert string to character stream using pointer.
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyDependents"})
public class InputCharacterStream {
    private final String sentence;
    private int pointer;

    public InputCharacterStream(String inputString) {
        this.sentence = inputString;
        pointer = 0;
    }

    public char getCurrentSymbol() {
        return sentence.charAt(pointer);
    }


    public void increasePointer() {
        ++pointer;
    }

    public boolean isEmpty() {
        return pointer == sentence.length();
    }

    public int getCurrentPointer(){return pointer;}

    public void setPointer(int position){pointer = position;}

}
