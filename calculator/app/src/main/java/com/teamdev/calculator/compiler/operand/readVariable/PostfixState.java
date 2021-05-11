package com.teamdev.calculator.compiler.operand.readVariable;

import com.teamdev.booby.compiler.unaryoperators.postfix.PostfixOperatorCompiler;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.doubleexpression.ExpressionCompiler;
import com.teamdev.calculator.compiler.operand.ExpressionState;
import com.teamdev.calculator.compiler.operand.OperandFiniteStateMachine;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.holder.ValueHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 *This state compile {@link PostfixOperatorCompiler postfix operator compiler}
 * and push command for {@link ShuntingYardStack stack} in {@link ReadVariableFSM read variable FSM}.
 * Also, it puts new variable value in {@link RuntimeEnvironment environment}.
 * */
public class PostfixState extends State<ShuntingYardStack> {

    private final Logger logger = LoggerFactory.getLogger(PostfixState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        logger.info("Try transition for postfix operator state");
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
                logger.info("Transition PostfixOperatorState successful");
                return true;
            }
        }
        return false;
    }
}
