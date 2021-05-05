package com.teamdev.calculator;

import com.teamdev.booby.Booby;
import com.teamdev.booby.impl.BoobyImpl;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.booleanexpression.BooleanExpressionFiniteStateMachine;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.runtime.BooleanScope;
import com.teamdev.calculator.runtime.Operator;
import com.teamdev.calculator.runtime.holder.ValueHolder;
import com.teamdev.calculator.runtime.holder.doubletype.DoubleValueHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BooleanExpression {
    static Stream<Arguments> positiveBooleanExpression() {
        return Stream.of(
                Arguments.of(false,"5<3"),
                Arguments.of(true,"min(7,4)>3"),
                Arguments.of(false,"4!=4"),
                Arguments.of(true,"9==9"),
                Arguments.of(true,"7>=2"),
                Arguments.of(true,"2>=2"),
                Arguments.of(false,"84<=9")
        );
    }

    @ParameterizedTest
    @MethodSource("positiveBooleanExpression")
    void executePositiveExpressionTest(boolean expected, String inputChain) {

        BooleanExpressionFiniteStateMachine machine = new BooleanExpressionFiniteStateMachine();
        InputCharacterStream stream = new InputCharacterStream(inputChain);
        BooleanScope scope = new BooleanScope();
        try {
            machine.execute(stream, scope);
        } catch (InvalidSymbolException | NotExistPairBracketException e) {
            e.printStackTrace();
        }
        Operator operator = scope.getOperator();
        ValueHolder<?> result = operator.apply(new DoubleValueHolder(scope.getOperands().get(0)), new DoubleValueHolder(scope.getOperands().get(1)));

        Assertions.assertEquals(expected, result.getValue());
    }
}
