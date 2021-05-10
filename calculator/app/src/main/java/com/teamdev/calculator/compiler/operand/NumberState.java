package com.teamdev.calculator.compiler.operand;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.number.NumberCompiler;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 *This state returns a command for {@link ShuntingYardStack stack} as a result of compiling the
 * {@link NumberCompiler number compiler} in {@link OperandFiniteStateMachine operand FSM}.
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyTransitiveDependencies"})
public class NumberState extends State<ShuntingYardStack> {

    private final Logger logger = LoggerFactory.getLogger(NumberState.class);
    private final CompilerFactory<ShuntingYardStack> compilerFactory;

    public NumberState(CompilerFactory<ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack stack) {

        logger.info("Try transition for number state");
        Optional<Command<ShuntingYardStack>> command = compilerFactory.create(TypeOfExpressionElement.NUMBER).compile(characterStream);
        if(command.isPresent()) {
            command.get().execute(stack);
            logger.info("Transition NumberState successful");
            return true;
        }

        return false;
    }
}
