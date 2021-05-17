package com.teamdev.calculator.compiler.function;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.command.FunctionCommand;
import com.teamdev.calculator.runtime.functions.FunctionScope;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.Optional;

/**
 * This is compiler for {@link FunctionFiniteStateMachine}.
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyTransitiveDependencies"})
public class FunctionCompiler implements ElementCompiler<ShuntingYardStack> {

    private final Logger logger = LoggerFactory.getLogger(FunctionCompiler.class);
    private final CompilerFactory<ShuntingYardStack> compilerFactory;
    private final StringBuilder writer;

    public FunctionCompiler(CompilerFactory<ShuntingYardStack> compilerFactory, StringBuilder writer) {
        this.compilerFactory = compilerFactory;
        this.writer = writer;
    }

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        logger.info("Start compile Function Compiler");
        FunctionFiniteStateMachine machine = new FunctionFiniteStateMachine(compilerFactory);
        FunctionScope scope = new FunctionScope(writer);
        if(machine.execute(stream, scope)){
            return Optional.of(new FunctionCommand(scope.getFunction(), scope.getArguments()));
        }

        return Optional.empty();
    }
}
