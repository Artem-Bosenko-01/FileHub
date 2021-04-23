package com.teamdev.calculator;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

public class NumberFiniteStateMachineTest {


    public static Stream<Arguments> positiveNumber(){
        return Stream.of(
                Arguments.of(4.0, "4"),
                Arguments.of(15.447, "15.447a"),
                Arguments.of(565.55, "565.55"),
                Arguments.of(44.0, "44asc4")
        );
    }

    @ParameterizedTest
    @MethodSource("positiveNumber")
    public void executeTest(double expected, String inputValue){
        InputCharacterStream stream = new InputCharacterStream(inputValue);
        ShuntingYardStack stack = new ShuntingYardStack();
        Optional<Command> command = new CompilerFactoryImpl().create(TypeOfExpressionElement.NUMBER).compile(stream);
        try {
            //noinspection OptionalGetWithoutIsPresent
            command.get().execute(stack);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(stack.peekOperand(), expected);
    }
}
