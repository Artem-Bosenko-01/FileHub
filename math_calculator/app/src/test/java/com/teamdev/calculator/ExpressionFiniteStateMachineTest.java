package com.teamdev.calculator;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.fsm.FiniteStateMachine;

import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ExpressionFiniteStateMachineTest {
/*
    FiniteStateMachineFactory factory = new FiniteStateMachineFactory();
    private final String inputValue;
    private final double expected;

    public ExpressionFiniteStateMachineTest(String inputValue, double expected) {
        this.inputValue = inputValue;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "execute expression FSM for {0} is {1}")
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"56+12-5-3+4", 64},
                {"36+12*7", 120},
                {"64/8+3",11}
        });
    }

    @Test
    public void executeTest(){
        InputCharacterStream stream = new InputCharacterStream(inputValue);
        ShuntingYardStack stack = new ShuntingYardStack();

        FiniteStateMachine machine = factory.create(FiniteStateMachineType.EXPRESSION);

        try {
            machine.execute(stream, stack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        double result = stack.calculate();
        Assert.assertEquals(result, expected, 0.0);
    }*/
}
