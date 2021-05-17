package com.teamdev.calculator.compiler.operand;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.command.OperandCommand;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.IllegalFormatException;
import java.util.Optional;

@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyTransitiveDependencies"})
public class OperandCompiler implements ElementCompiler<ShuntingYardStack> {

    private final Logger logger = LoggerFactory.getLogger(OperandCompiler.class);
    private final CompilerFactory<ShuntingYardStack> compilerFactory;
    public OperandCompiler(CompilerFactory<ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        logger.info("Start compile Operand Compiler");
        OperandFiniteStateMachine machine = new OperandFiniteStateMachine(compilerFactory);
        ShuntingYardStack stack = new ShuntingYardStack();
        try {
            if(machine.execute(stream, stack)){
                return Optional.of(new OperandCommand(stack.calculate()));
            }
        } catch (IllegalFormatException e) {
            logger.error(e.getMessage());
        }
        return Optional.empty();
    }
}
