package com.teamdev.calculator;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.exception.InvalidSymbolException;
import com.teamdev.calculator.impl.CompilerFactoryImpl;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
class NegativeNumberFiniteStateMachineTest {

    static Stream<Arguments> negativeNumberExpression(){
        return Stream.of(
                Arguments.of(new InvalidSymbolException(2),"sc4"),
                Arguments.of(new InvalidSymbolException(1),"5.asca4"),
                Arguments.of(new InvalidSymbolException(1), "--5")
        );
    }

    @ParameterizedTest
    @MethodSource("negativeNumberExpression")
    void executePositiveExpressionTest(Exception expected, String inputChain){
        InputCharacterStream stream = new InputCharacterStream(inputChain);
        ShuntingYardStack stack = new ShuntingYardStack();
        Optional<Command<ShuntingYardStack>> command = new CompilerFactoryImpl().create(TypeOfExpressionElement.NUMBER).compile(stream);

        command.ifPresent(shuntingYardStackCommand -> Assertions.assertThrows(expected.getClass(), () -> shuntingYardStackCommand.execute(stack)));
    }
}
