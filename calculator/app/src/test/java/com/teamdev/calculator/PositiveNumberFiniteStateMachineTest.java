package com.teamdev.calculator;


import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
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
class PositiveNumberFiniteStateMachineTest {


    static Stream<Arguments> positiveNumber(){
        return Stream.of(
                Arguments.of(4.0, "4"),
                Arguments.of(15.447, "15.447a"),
                Arguments.of(565.55, "565.55"),
                Arguments.of(44.0, "44asc4"),
                Arguments.of(56.0, "56")
        );
    }

    @ParameterizedTest
    @MethodSource("positiveNumber")
    void executeTest(double expected, String inputValue){
        InputCharacterStream stream = new InputCharacterStream(inputValue);
        ShuntingYardStack stack = new ShuntingYardStack();

        Optional<Command<ShuntingYardStack>> command = new CompilerFactoryImpl().create(TypeOfExpressionElement.NUMBER).compile(stream);
        try {
            command.get().execute(stack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(stack.peekOperand().isPresent()){
            Assertions.assertEquals(stack.peekOperand().get().getValue(), expected);
        }
    }
}
