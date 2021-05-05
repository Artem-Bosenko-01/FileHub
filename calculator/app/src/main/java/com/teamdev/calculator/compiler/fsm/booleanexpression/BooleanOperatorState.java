package com.teamdev.calculator.compiler.fsm.booleanexpression;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;
import com.teamdev.calculator.runtime.BooleanBinaryOperator;
import com.teamdev.calculator.runtime.BooleanScope;

public class BooleanOperatorState extends State<BooleanScope> {

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, BooleanScope result) {
        BooleanOperatorFiniteStateMachine machine = new BooleanOperatorFiniteStateMachine();
        StringBuilder builder = new StringBuilder(10);
        try {
            if(machine.execute(characterStream, builder)){
                BooleanBinaryOperator operator = BooleanOperatorFactory.getOperator(builder.toString());
                result.setOperator(operator);
                return true;
            }
        } catch (InvalidSymbolException | NotExistPairBracketException e) {
            e.printStackTrace();
        }

        return false;
    }
}
