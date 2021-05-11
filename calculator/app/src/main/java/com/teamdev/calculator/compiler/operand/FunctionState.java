package com.teamdev.calculator.compiler.operand;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.function.FunctionCompiler;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 *This state returns a command for {@link ShuntingYardStack stack} as a result of compiling the
 * {@link FunctionCompiler function compiler} in {@link OperandFiniteStateMachine operand FSM}.
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyTransitiveDependencies"})
public class FunctionState extends State<ShuntingYardStack> {
    private final Logger logger = LoggerFactory.getLogger(FunctionState.class);
    private final CompilerFactory<ShuntingYardStack> compilerFactory;

    public FunctionState(CompilerFactory<ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {
        logger.info("Try transition for function state");
        Optional<Command<ShuntingYardStack>> command = compilerFactory.create(TypeOfExpressionElement.FUNCTION).compile(characterStream);

        if(command.isPresent()){
            command.get().execute(builder);
            logger.info("Transition FunctionState successful");
            return true;
        }
        return false;
    }
}
