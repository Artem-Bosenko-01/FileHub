package com.teamdev.calculator.fsm.expression;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.fsm.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.operators.BinaryOperator;

public class OperatorState extends State<ShuntingYardStack> {

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack outputChain) {
        if(String.valueOf(characterStream.getCurrentSymbol()).matches("[-*/^+]")){
            StringBuilder builder = new StringBuilder();
            builder.append(characterStream.getCurrentSymbol());
            BinaryOperator operator = ChooseOperator.getOperator(builder.toString());
            outputChain.pushOperator(operator);
            characterStream.increasePointer();
            return true;
        }
        return false;
    }
}
