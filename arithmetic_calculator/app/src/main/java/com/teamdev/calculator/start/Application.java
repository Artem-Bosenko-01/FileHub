package com.teamdev.calculator.start;

import com.teamdev.calculator.base.InputCharacterStream;
import com.teamdev.calculator.number.NumberFiniteStateMachine;
import com.teamdev.calculator.base.io.Scanner;

public class Application {
    private static final StringBuilder result = new StringBuilder();

    public static void run(){
        //Scanner.output("Please, input value");
        String str = "5654.4sdsd55";
        Scanner.output("Our input string - " + str);
        InputCharacterStream characterStream = new InputCharacterStream(str);
        NumberFiniteStateMachine stateMachine = new NumberFiniteStateMachine();

        try {
            stateMachine.execute(characterStream, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        double d = Double.parseDouble(result.toString());
        Scanner.output("Double value = " + String.valueOf(d));
    }
}
