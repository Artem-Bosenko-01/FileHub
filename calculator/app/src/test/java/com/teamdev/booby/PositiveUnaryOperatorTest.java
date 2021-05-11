package com.teamdev.booby;

import com.teamdev.booby.impl.BoobyCompilerFactoryImpl;
import com.teamdev.booby.impl.BoobyImpl;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PositiveUnaryOperatorTest {




    static Stream<Arguments> positiveUnaryOperatorExpression(){
        return Stream.of(
                Arguments.of("c=2;println(++c);println(c)"),
                Arguments.of("c=5;println(c++);println(c)"),
                Arguments.of("a=1;println(a++);println(a);"),
                Arguments.of("a=5;println(a--);println(a);"),
                Arguments.of("a=1;println(++a);println(a);"),
                Arguments.of("a=5;println(--a);println(a);")
        );
    }

    @ParameterizedTest
    @MethodSource("positiveUnaryOperatorExpression")
    void executePositiveExpressionTest(String inputChain){
        System.out.println(inputChain);
        Booby calculator = new BoobyImpl(new BoobyCompilerFactoryImpl());
        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();
        calculator.execute(inputChain, environment);
    }

}
