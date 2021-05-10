package com.teamdev.calculator.compiler.function;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.command.FunctionCommand;
import com.teamdev.calculator.runtime.functions.FunctionScope;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * This is compiler for {@link FunctionFiniteStateMachine}.
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyTransitiveDependencies"})
public class FunctionCompiler implements ElementCompiler<ShuntingYardStack> {

    private final Logger logger = LoggerFactory.getLogger(FunctionCompiler.class);
    private final CompilerFactory<ShuntingYardStack> compilerFactory;

    public FunctionCompiler(CompilerFactory<ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        logger.info("Start compile Function Compiler");
        FunctionFiniteStateMachine machine = new FunctionFiniteStateMachine(compilerFactory);
        FunctionScope scope = new FunctionScope();
        if(machine.execute(stream, scope)){
            return Optional.of(new FunctionCommand(scope.getFunction(), scope.getArguments()));
        }

        return Optional.empty();
    }
}
