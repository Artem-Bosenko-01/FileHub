package com.teamdev.calculator.fsm.expression;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.fsm.State;
import com.teamdev.calculator.fsm.number.NumberFiniteStateMachine;
import com.teamdev.calculator.runtime.ShuntingYardStack;


public class NumberState extends State<ShuntingYardStack> {

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack stack) {

        NumberFiniteStateMachine machine = new NumberFiniteStateMachine();
        try {
            StringBuilder builder = new StringBuilder();
            if(machine.execute(characterStream, builder)){
                stack.pushOperand(Double.parseDouble(builder.toString()));
                CleanBuilderUtil.clean(builder);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
