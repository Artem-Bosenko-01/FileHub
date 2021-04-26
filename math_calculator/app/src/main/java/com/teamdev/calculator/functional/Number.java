package com.teamdev.calculator.functional;

import com.teamdev.calculator.compiler.InputCharacterStream;

import static com.teamdev.calculator.functional.Predicates.*;

public class Number {
    private static Acceptor digit(){
        return characterAcceptor(Character::isDigit);
    }

    public static Acceptor number(){
        return chain(optional(character('-')), loop(digit()), optional(chain(character('.'), digit())));
    }

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        number().execute(new InputCharacterStream("665.sdc"), builder);
        System.out.println(builder.toString());
    }
}
