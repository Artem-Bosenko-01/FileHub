package com.teamdev.calculator.start;

import com.teamdev.calculator.base.InputCharacterStream;
import com.teamdev.calculator.base.io.Scanner;
import com.teamdev.calculator.expression.ExpressionFiniteStateMachine;
import com.teamdev.calculator.expression.ShuntingYardStack;

public class Application {
    private static final StringBuilder result = new StringBuilder();
    private static final ShuntingYardStack resultYard = new ShuntingYardStack();

    public static void run(){
        //Scanner.output("Please, input value");
        String str = "52/56+1";
        Scanner.output("Our input string - " + str);
        InputCharacterStream characterStream = new InputCharacterStream(str);

        ExpressionFiniteStateMachine<ShuntingYardStack> machine = new ExpressionFiniteStateMachine<ShuntingYardStack>();

        try {
            machine.execute(characterStream,resultYard);
        } catch (Exception e) {
            e.printStackTrace();
        }

        double d = resultYard.calculate();
        Scanner.output("Double value = " + String.valueOf(d));
    }
}
