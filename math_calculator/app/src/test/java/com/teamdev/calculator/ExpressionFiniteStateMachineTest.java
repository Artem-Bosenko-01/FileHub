package com.teamdev.calculator;

import com.teamdev.calculator.base.InputCharacterStream;
import com.teamdev.calculator.expression.ExpressionFiniteStateMachine;
import com.teamdev.calculator.expression.ShuntingYardStack;
import com.teamdev.calculator.number.NumberFiniteStateMachine;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ExpressionFiniteStateMachineTest {

    private final String inputValue;
    private final double expected;

    public ExpressionFiniteStateMachineTest(String inputValue, double expected) {
        this.inputValue = inputValue;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index} execute FSM for {0} is {1}")
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"56+12-5-3+4", 64},{"36+12*7", 120},{"64/8+3",11}
        });
    }

    @Test
    public void executeTest(){
        InputCharacterStream stream = new InputCharacterStream(inputValue);
        ShuntingYardStack stack = new ShuntingYardStack();
        ExpressionFiniteStateMachine machine = new ExpressionFiniteStateMachine();

        try {
            machine.execute(stream, stack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        double result = stack.calculate();
        Assert.assertEquals(result, expected, 0.0);
    }
}
