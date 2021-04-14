package arithmetic_calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

//@RunWith(Parameterized.class)
public class InputCharacterStreamTest {

    private final String value;

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {"155.65aasc"},{"s56a"}, {"cascscs"}
        });
    }
    public InputCharacterStreamTest(String value) {
        this.value = value;
    }

}
