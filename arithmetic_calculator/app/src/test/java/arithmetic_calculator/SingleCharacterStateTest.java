package arithmetic_calculator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import com.teamdev.calculator.base.state.State;
import com.teamdev.calculator.number.SingleCharacterState;

import java.util.Arrays;
import java.util.Collection;

/*@RunWith(Parameterized.class)
public class SingleCharacterStateTest {

    private final char inputValue;
    private final char[] integerValue;
    private final boolean expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {'.',new char[]{'.',';','/'},true},{'2',new char[]{'.',';','2'},false}
        });
    }

    public SingleCharacterStateTest(char inputValue,char[] integerValue, boolean expected) {
        this.inputValue = inputValue;
        this.expected = expected;
        this.integerValue = integerValue;
    }


    @Test
    public void tryTransitionTest(){
        SingleCharacterState digitState = new SingleCharacterState();

        for (char s: integerValue) {
            State state = new SingleCharacterState();
            digitState.addTransition(state);
        }

        StringBuilder s = new StringBuilder();
        Assert.assertEquals(digitState.tryTransition(inputValue, s), expected);
    }

    @Test
    public void addTransitionTest(){
        SingleCharacterState state = new SingleCharacterState();

        for (char s: integerValue) {
            State state1 = new SingleCharacterState();
            state.addTransition(state1);
        }

        Assert.assertNotNull(state.getTransitions());
    }
}*/
