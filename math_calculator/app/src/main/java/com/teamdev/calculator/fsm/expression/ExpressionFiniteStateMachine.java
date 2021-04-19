package com.teamdev.calculator.fsm.expression;

import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.fsm.FiniteStateMachine;

import java.util.Arrays;

public class ExpressionFiniteStateMachine extends FiniteStateMachine<ShuntingYardStack> {

    public ExpressionFiniteStateMachine() {
        NumberState number = new NumberState();
        OperatorState operator = new OperatorState();

        number.addTransition(operator);
        operator.addTransition(number);

        setStartStates(Arrays.asList(number));
        setFinishStates(Arrays.asList(number));
    }


    /*@Override
    public boolean execute(InputCharacterStream inputCharacterStream, T outputChain) throws Exception {

        NumberFiniteStateMachine finiteStateMachine = new NumberFiniteStateMachine();
        StringBuilder builder = new StringBuilder();

        while (!inputCharacterStream.isEmpty()) {

            boolean result = finiteStateMachine.execute(inputCharacterStream, builder);
            Double operand = Double.parseDouble(builder.toString());
            ((ShuntingYardStack) outputChain).pushOperand(operand);
            CleanBuilderUtil.clean(builder);

            if (result && !inputCharacterStream.isEmpty()) {
                State operatorState = new OperatorState();
                result = operatorState.tryTransition(inputCharacterStream, builder);
                if (result) {

                    BinaryOperator operator = ChooseOperator.getOperator(builder.toString());
                    ((ShuntingYardStack) outputChain).pushOperator(operator);
                    CleanBuilderUtil.clean(builder);

                } else {
                    return false;
                }
            }
        }
        return true;
    }*/
}