package com.teamdev.calculator.compiler.doubleexpression;


import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.operand.OperandCompiler;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Optional;

/**
 *This state compile {@link OperandCompiler operand compiler}
 * and push command for {@link ShuntingYardStack stack} in {@link ExpressionFiniteStateMachine expression FSM}
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyTransitiveDependencies"})
public class OperandState extends State<ShuntingYardStack> {
    private final Logger logger = LoggerFactory.getLogger(OperandState.class);
    private final CompilerFactory<TypeOfExpressionElement,ShuntingYardStack> compilerFactory;

    public OperandState(CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {

        logger.info("Try transition for operand state");

        Optional<Command<ShuntingYardStack>> command = compilerFactory
                .create(TypeOfExpressionElement.OPERAND)
                .compile(characterStream);

        try {
            if(command.isPresent()){
                command.get().execute(builder);
                logger.info("Transition OperandState successful");
                return true;
            }
        } catch (SyntaxException e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}
