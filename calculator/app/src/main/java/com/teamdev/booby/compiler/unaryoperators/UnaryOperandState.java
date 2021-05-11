package com.teamdev.booby.compiler.unaryoperators;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.booby.runtime.UnaryOperatorOutputChain;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.holder.ValueHolder;
import com.teamdev.calculator.runtime.holder.value.DoubleVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * This is one of states for
 * {@link com.teamdev.booby.compiler.unaryoperators.postfix.PostfixOperatorFiniteStateMachine postfix} or
 * {@link com.teamdev.booby.compiler.unaryoperators.prefix.PrefixOperatorFiniteStateMachine}, that defines value for
 * {@link UnaryOperatorOutputChain  output chain}.
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class UnaryOperandState extends State<UnaryOperatorOutputChain> {
    private final Pattern pattern = Pattern.compile("[a-zA-Z]");
    private final Logger logger = LoggerFactory.getLogger(UnaryOperandState.class);

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, UnaryOperatorOutputChain builder) {

        logger.info("Try transition unary operand state");
        RuntimeEnvironment environment = RuntimeEnvironment.getInstance();

        if (pattern.matcher(String.valueOf(characterStream.getCurrentSymbol())).matches()) {
            ValueHolder<?> valueHolder = environment.getValue(String.valueOf(characterStream.getCurrentSymbol()));
            Optional<Double> value = new DoubleVisitor().getDoubleValue(valueHolder);
            if(value.isPresent()) {
                builder.setOperand(value.get());
                characterStream.increasePointer();
                logger.info("Transition UnaryOperandState successful");
                return true;
            }
        }
        return false;
    }
}
