package com.teamdev.calculator.compiler.operand.readVariable;

import com.teamdev.booby.compiler.unaryoperators.postfix.PostfixOperatorCompiler;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.Optional;

public class PostfixState extends State<ShuntingYardStack> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {

        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();
        ValueHolder<?> value = environment.getValue(String.valueOf(characterStream.getCurrentSymbol()));
        String currentSymbol = String.valueOf(characterStream.getCurrentSymbol());
        Optional<Command<ShuntingYardStack>> command = new PostfixOperatorCompiler().compile(characterStream);
        ShuntingYardStack secondStack = new ShuntingYardStack();


        if (command.isPresent()){
            command.get().execute(secondStack);
            if(value != null) {
                builder.pushOperand(value);
                environment.setVariable(currentSymbol, secondStack.calculate());
                return true;
            }
        }
        return false;
    }
}
