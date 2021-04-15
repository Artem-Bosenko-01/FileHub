package com.teamdev.calculator;

import com.teamdev.calculator.base.InputCharacterStream;
import com.teamdev.calculator.number.NumberFiniteStateMachine;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NumberFiniteStateMachineTest {

    private final String inputValue;
    private final boolean expected;

    public NumberFiniteStateMachineTest(String inputValue, boolean expected) {
        this.inputValue = inputValue;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index} execute FSM for {0} is {1}")
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"56",true},{"56.s55",false},{"12.64assa55s4",true}, {"a.564",false}
        });
    }

    @Test
    public void executeTest(){
        InputCharacterStream stream = new InputCharacterStream(inputValue);
        StringBuilder builder = new StringBuilder();
        NumberFiniteStateMachine machine = new NumberFiniteStateMachine();
        boolean result = false;
        try {
            result = machine.execute(stream, builder);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals(result, expected);
    }
}
