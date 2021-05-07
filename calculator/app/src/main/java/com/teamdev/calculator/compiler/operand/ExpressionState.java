package com.teamdev.calculator.compiler.operand;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.doubleexpression.ExpressionCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 *This state compile {@link ExpressionCompiler expression compiler}
 * and push command for {@link ShuntingYardStack stack} in {@link OperandFiniteStateMachine operand FSM}
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyTransitiveDependencies"})
public class ExpressionState extends State<ShuntingYardStack> {

    private final Logger logger = LoggerFactory.getLogger(ExpressionState.class);
    private final CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory;

    public ExpressionState(CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        logger.info("Try transition for EXPRESSION state");
        Optional<Command<ShuntingYardStack>> command = compilerFactory.create(TypeOfExpressionElement.EXPRESSION).compile(characterStream);
        if (command.isPresent()){
            command.get().execute(builder);
            logger.info("Transition ExpressionState successful");
            return true;
        }
        return false;
    }
}
