package com.teamdev.booby;

import com.teamdev.booby.impl.BoobyImpl;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PositiveInitVariableTest {

    static Stream<Arguments> positiveInitVariable(){
        return Stream.of(
                Arguments.of("a=5.5;b=a+5;c=a+b;println(c);"),
                Arguments.of("a=5;b=7;c=avg(a,b);println(a,b,c)"),
                Arguments.of("a=2;l=a^a;println(l);b=l+1;println(a)"),
                Arguments.of("a=5<3;println(a);a=6;println(a)"),
                Arguments.of("a=0;a=a+1;println(a)")
        );
    }

    @ParameterizedTest
    @MethodSource("positiveInitVariable")
    void executePositiveExpressionTest(String inputChain){
        System.out.println(inputChain);
        Booby calculator = new BoobyImpl();
        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();
        calculator.execute(inputChain, environment);
    }
}
