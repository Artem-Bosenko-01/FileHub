package com.teamdev.calculator;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

class NegativeExpressionFiniteStateMachineTest {
    static Stream<Arguments> negativeExpression(){
        return Stream.of(
                Arguments.of(new InvalidSymbolException(2),"2++5"),
                Arguments.of(new InvalidSymbolException(1),"--5"),
                Arguments.of(new InvalidSymbolException(1), "2+5**4"),
                Arguments.of(new NotExistPairBracketException(),"5+(4-2*5"),
                Arguments.of(new NotExistPairBracketException(), "4-5+1*)")
        );
    }

    @ParameterizedTest
    @MethodSource("negativeExpression")
    void executePositiveExpressionTest(Exception expected, String inputChain){
        InputCharacterStream stream = new InputCharacterStream(inputChain);
        ShuntingYardStack stack = new ShuntingYardStack();
        Optional<Command<ShuntingYardStack>> command = new CompilerFactoryImpl().create(TypeOfExpressionElement.EXPRESSION).compile(stream);

        command.ifPresent(shuntingYardStackCommand -> Assertions.assertThrows(expected.getClass(), () -> shuntingYardStackCommand.execute(stack)));

    }
}
