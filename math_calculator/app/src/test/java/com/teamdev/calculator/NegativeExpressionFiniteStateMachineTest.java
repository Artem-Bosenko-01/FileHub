package com.teamdev.calculator;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.ExpressionFiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class NegativeExpressionFiniteStateMachineTest {
    public static Stream<Arguments> negativeExpression(){
        return Stream.of(
                Arguments.of(new InvalidSymbolException(2),"2++5"),
                Arguments.of(new InvalidSymbolException(1),"--5"),
                Arguments.of(new InvalidSymbolException(1), "2+5**4")
        );
    }

    @ParameterizedTest
    @MethodSource("negativeExpression")
    public void executePositiveExpressionTest(Exception expected, String inputChain){
        InputCharacterStream stream = new InputCharacterStream(inputChain);
        ShuntingYardStack stack = new ShuntingYardStack();
        ExpressionFiniteStateMachine machine = new ExpressionFiniteStateMachine();

        Assertions.assertThrows(RuntimeException.class, ()->machine.execute(stream,stack));
    }
}
