package com.teamdev.calculator.compiler.operand.readVariable;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.holder.ValueHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * This state push new operand by value for variable name in {@link RuntimeEnvironment environment}.
 * */
public class VariableState extends State<ShuntingYardStack> {

    private final Pattern pattern = Pattern.compile("[a-zA-Z]");
    private final Logger logger = LoggerFactory.getLogger(VariableState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        logger.info("Try transition for variable value state");
        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();

        if (pattern.matcher(String.valueOf(characterStream.getCurrentSymbol())).matches()) {
            ValueHolder<?> value = environment.getValue(String.valueOf(characterStream.getCurrentSymbol()));
            if(value != null) {
                builder.pushOperand(value);
                characterStream.increasePointer();
                logger.info("Transition VariableValueState successful");
                return true;
            }
        }
        return false;
    }
}
