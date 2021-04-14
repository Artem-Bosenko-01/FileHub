package arithmetic_calculator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.teamdev.calculator.number.DigitState;

import java.util.Arrays;
import java.util.Collection;

/*
@RunWith(Parameterized.class)
public class DigitStateTest {

    private final char inputValue;
    private final boolean expected;

    @Parameterized.Parameters(name = "{index} digit state transition for {0} is {1}")
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {'1',true},{'2',true},{'a',false}
        });
    }

    public DigitStateTest(char inputValue, boolean expected) {
        this.inputValue = inputValue;
        this.expected = expected;

    }


    @Test
    public void tryTransitionTest(){
        DigitState digitState = new DigitState();

        StringBuilder builder = new StringBuilder();
        Assert.assertEquals(digitState.tryTransition(inputValue, builder), expected);
    }

    @Test
    public void addTransitionTest(){
        DigitState state = new DigitState();
        Assert.assertNotNull(state.getTransitions());
    }
}
*/
