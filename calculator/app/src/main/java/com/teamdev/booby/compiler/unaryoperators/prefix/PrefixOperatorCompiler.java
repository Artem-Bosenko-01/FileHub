package com.teamdev.booby.compiler.unaryoperators.prefix;

import com.teamdev.booby.compiler.statement.StatementCompiler;
import com.teamdev.booby.compiler.unaryoperators.postfix.PostfixOperatorFiniteStateMachine;
import com.teamdev.booby.runtime.UnaryOperatorOutputChain;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.holder.value.DoubleValueHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * This is compiler, that processes {@link PrefixOperatorFiniteStateMachine prefix operator FSM}.
 *  As a result of compiling pushes new operator in {@link ShuntingYardStack stack}.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class PrefixOperatorCompiler implements ElementCompiler<ShuntingYardStack> {

    private final Logger logger = LoggerFactory.getLogger(PrefixOperatorCompiler.class);
    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        logger.info("Start compile Prefix operator Compiler");

        PrefixOperatorFiniteStateMachine machine = new PrefixOperatorFiniteStateMachine();
        UnaryOperatorOutputChain unaryOperatorOutputChain = new UnaryOperatorOutputChain();

        if(machine.execute(stream,unaryOperatorOutputChain)){
            logger.info("Prefix operator compiler execute successful");
            return Optional.of(stack -> stack.pushOperand(new DoubleValueHolder(unaryOperatorOutputChain.execute())));
        }
        return Optional.empty();
    }
}
