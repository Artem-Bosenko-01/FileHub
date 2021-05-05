package com.teamdev.calculator.compiler.fsm.booleanexpression;

import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;

import com.teamdev.calculator.compiler.impl.OperandCompiler;
import com.teamdev.calculator.runtime.BooleanScope;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;

import java.util.Optional;

public class BooleanOperandState extends State<BooleanScope> {

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, BooleanScope builder) {
        ElementCompiler<ShuntingYardStack> compiler = new OperandCompiler();
        Optional<Command<ShuntingYardStack>> command = compiler.compile(characterStream);
        ShuntingYardStack stack = new ShuntingYardStack();
        if(command.isPresent()){
            command.get().execute(stack);
            builder.addOperand(stack.calculate());
            return true;
        }
        return false;
    }
}
