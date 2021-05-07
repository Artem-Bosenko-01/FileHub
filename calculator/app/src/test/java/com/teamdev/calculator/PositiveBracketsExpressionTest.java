package com.teamdev.calculator;

import com.teamdev.calculator.impl.CalculatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
class PositiveBracketsExpressionTest {


    static Stream<Arguments> positiveExpression(){
        return Stream.of(
                Arguments.of(36,"(2*3)^2"),
                Arguments.of(20.0,"(5*2)+10"),
                Arguments.of(42.0,"8+(21-4)*2"),
                Arguments.of(56.0,"(2+5)*8"),
                Arguments.of(-116,"3*4-2^(12-4/2-3)")
        );
    }

    @ParameterizedTest
    @MethodSource("positiveExpression")
    void executePositiveExpressionTest(double expected, String inputChain){
        Calculator calculator = new CalculatorImpl();
        Optional<Double> result = calculator.calculate(inputChain);
        Assertions.assertEquals(expected, result.get());
    }

}
