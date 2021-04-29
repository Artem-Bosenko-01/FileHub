package com.teamdev.calculator.compiler.fsm.operand;

import com.teamdev.calculator.CompilerFactoryImpl;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.impl.FunctionCompiler;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Optional;

/**
 *This state returns a command for {@link ShuntingYardStack stack} as a result of compiling the
 * {@link FunctionCompiler function compiler} in {@link OperandFiniteStateMachine operand FSM}
 * */
public class FunctionState extends State<ShuntingYardStack> {
    private final Logger logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(FunctionState.class);

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        logger.info("Try transition for function state");
        Optional<Command<ShuntingYardStack>> command = new CompilerFactoryImpl().create(TypeOfExpressionElement.FUNCTION).compile(characterStream);

        if(command.isPresent()){
            command.get().execute(builder);
            return true;
        }
        logger.info("Transition successful");
        return false;
    }
}
