package com.teamdev.calculator.compiler.fsm.operand;

import com.teamdev.calculator.CompilerFactoryImpl;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.Optional;

/**
 *This state compile {@link com.teamdev.calculator.compiler.impl.ExpressionCompiler expression compiler}
 * and push command for {@link ShuntingYardStack stack} in {@link OperandFiniteStateMachine operand FSM}
 * */
public class ExpressionState extends State<ShuntingYardStack> {

    private final Logger logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(ExpressionState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        logger.info("Try transition for EXPRESSION state");
        CompilerFactory factory = new CompilerFactoryImpl();
        Optional<Command<ShuntingYardStack>> command = factory.create(TypeOfExpressionElement.EXPRESSION).compile(characterStream);
        if (command.isPresent()){
            command.get().execute(builder);
            logger.info("Transition successful");
            return true;
        }
        return false;
    }
}
