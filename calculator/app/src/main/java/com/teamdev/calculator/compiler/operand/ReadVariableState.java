package com.teamdev.calculator.compiler.operand;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.operand.readVariable.ReadVariableCompiler;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;

import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class ReadVariableState extends State<ShuntingYardStack> {


    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {

        Optional<Command<ShuntingYardStack>> command = new ReadVariableCompiler().compile(characterStream);

        if(command.isPresent()){
            command.get().execute(builder);
            return true;
        }
        return false;
    }
}
