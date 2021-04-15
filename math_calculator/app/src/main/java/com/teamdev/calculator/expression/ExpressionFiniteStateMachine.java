package com.teamdev.calculator.expression;

import com.teamdev.calculator.base.InputCharacterStream;
import com.teamdev.calculator.base.finite_state_machine.FiniteStateMachine;
import com.teamdev.calculator.base.state.State;
import com.teamdev.calculator.expression.operators.BinaryOperator;
import com.teamdev.calculator.number.NumberFiniteStateMachine;

import java.util.List;

public class ExpressionFiniteStateMachine<T extends ShuntingYardStack> extends FiniteStateMachine<T> {

    public ExpressionFiniteStateMachine() {
        super();
    }

    public ExpressionFiniteStateMachine(List<State> startStates) {
        super(startStates);
    }

    @Override
    public boolean execute(InputCharacterStream inputCharacterStream, T outputChain) throws Exception {

        ShuntingYardStack stack = (ShuntingYardStack) outputChain;

        NumberFiniteStateMachine<StringBuilder> finiteStateMachine = new NumberFiniteStateMachine<StringBuilder>();
        StringBuilder builder = new StringBuilder();

        while (!inputCharacterStream.isEmpty()) {

            boolean result = finiteStateMachine.execute(inputCharacterStream, builder);
            Double operand = Double.parseDouble(builder.toString());
            stack.pushOperand(operand);
            CleanBuilderUtil.clean(builder);

            if (result && !inputCharacterStream.isEmpty()){
                State operatorState = new OperatorState();
                result = operatorState.tryTransition(inputCharacterStream, builder);
                if (result) {

                    BinaryOperator operator = ChooseOperator.getOperator(builder.toString());
                    stack.pushOperator(operator);
                    CleanBuilderUtil.clean(builder);

                }else return false;

            }

        }
        return true;
    }
}