package com.teamdev.calculator.compiler.booleanexpression;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.operators.BooleanScope;
import com.teamdev.calculator.runtime.operators.Operator;

public class BooleanOperatorState extends State<BooleanScope> {

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, BooleanScope result) {
        BooleanOperatorFiniteStateMachine machine = new BooleanOperatorFiniteStateMachine();
        StringBuilder builder = new StringBuilder(10);
        if(machine.execute(characterStream, builder)){
            Operator operator = BooleanOperatorFactory.getOperator(builder.toString());
            result.setOperator(operator);
            return true;
        }

        return false;
    }
}
