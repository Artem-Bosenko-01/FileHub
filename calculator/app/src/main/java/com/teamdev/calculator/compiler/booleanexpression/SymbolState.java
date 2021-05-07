package com.teamdev.calculator.compiler.booleanexpression;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;

import java.util.regex.Pattern;

public class SymbolState extends State<StringBuilder> {

    private final Pattern pattern = Pattern.compile("[<=>!]");

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {

        if (pattern.matcher(String.valueOf(characterStream.getCurrentSymbol())).matches()) {

            builder.append(characterStream.getCurrentSymbol());
            characterStream.increasePointer();
            return true;
        }

        return false;
    }
}
