package com.teamdev.calculator;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.fsm.number.NumberFiniteStateMachine;

public class Main {
    public static void main(String[] args) {
        InputCharacterStream s = new InputCharacterStream("894.44");
        NumberFiniteStateMachine f = new NumberFiniteStateMachine();
        StringBuilder b = new StringBuilder();
        try {
            f.execute(s,b);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(b.toString());
    }
}
