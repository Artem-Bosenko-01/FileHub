package com.teamdev.calculator.compiler.operand.readVariable;

import com.teamdev.booby.compiler.unaryoperators.prefix.PrefixOperatorCompiler;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;

import java.util.Optional;

public class PrefixState extends State<ShuntingYardStack> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {

        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();
        Optional<Command<ShuntingYardStack>> command = new PrefixOperatorCompiler().compile(characterStream);
        String currentSymbol = String.valueOf(characterStream.getPreviousSymbol());
        characterStream.increasePointer();
        if(command.isPresent()){
            command.get().execute(builder);
            environment.setVariable(currentSymbol,builder.peekOperand().get());
            return true;
        }

        return false;
    }
}
