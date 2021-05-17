package com.teamdev.calculator;

import com.teamdev.calculator.impl.CalculatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.PrintWriter;
import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
class PositiveExpressionFunctionTest {
    static Stream<Arguments> positiveExpression(){
        return Stream.of(
                Arguments.of(6,"min(5+2,6)"),
                Arguments.of(8,"max(5,7,6,8,4)"),
                Arguments.of(-0.9055783620066238,"sin(8+8+8)"),
                Arguments.of(0.5101770449416689,"cos(89)"),
                Arguments.of(3.2188758248682006,"log(5*5)"),
                Arguments.of(1.3979400086720377,"log10(25)"),
                Arguments.of(32,"max(5,min(32,45),8,4)"),
                Arguments.of(5,"avg(max(2,3),7)"),
                Arguments.of(7,"max(1,5,6,7)"),
                Arguments.of(15,"sum(min(1*3,5),5,7)"),
                Arguments.of(3.141592653589793,"pi()"),
                Arguments.of(6.141592653589793,"sum(min(1*3,5),pi())")
        );
    }

    @ParameterizedTest
    @MethodSource("positiveExpression")
    void executePositiveExpressionTest(double expected, String inputChain){
        Calculator calculator = new CalculatorImpl(new StringBuilder());
        Optional<Double> result = calculator.calculate(inputChain);
        result.ifPresent(aDouble -> Assertions.assertEquals(expected, aDouble));
    }

}
