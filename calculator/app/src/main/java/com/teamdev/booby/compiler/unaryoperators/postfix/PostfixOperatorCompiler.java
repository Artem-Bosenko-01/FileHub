package com.teamdev.booby.compiler.unaryoperators.postfix;

import com.teamdev.booby.compiler.statement.StatementFiniteStateMachine;
import com.teamdev.booby.compiler.unaryoperators.prefix.PrefixOperatorCompiler;
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
 * This is compiler, that processes {@link PostfixOperatorFiniteStateMachine postfix operator FSM}.
 *  As a result of compiling pushes new operator in {@link ShuntingYardStack stack}.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class PostfixOperatorCompiler implements ElementCompiler<ShuntingYardStack> {
    private final Logger logger = LoggerFactory.getLogger(PostfixOperatorCompiler.class);
    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        logger.info("Start compile Postfix operator Compiler");
        PostfixOperatorFiniteStateMachine machine = new PostfixOperatorFiniteStateMachine();
        UnaryOperatorOutputChain unaryOperatorOutputChain = new UnaryOperatorOutputChain();

        if(machine.execute(stream,unaryOperatorOutputChain)){
            logger.info("Postfix operator compiler execute successful");
            return Optional.of(stack -> stack.pushOperand(new DoubleValueHolder(unaryOperatorOutputChain.execute())));
        }
        return Optional.empty();
    }
}
