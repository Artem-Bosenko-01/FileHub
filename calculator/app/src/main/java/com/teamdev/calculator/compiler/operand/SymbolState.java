package com.teamdev.calculator.compiler.operand;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.regex.Pattern;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class SymbolState extends State<ShuntingYardStack> {

    private final Pattern pattern = Pattern.compile("[a-zA-Z]");

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {

        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();

        if (pattern.matcher(String.valueOf(characterStream.getCurrentSymbol())).matches()) {
            ValueHolder<?> value = environment.getValue(String.valueOf(characterStream.getCurrentSymbol()));
            if(value != null) {
                builder.pushOperand(value);
                characterStream.increasePointer();
                return true;
            }
        }
        return false;
    }
}
