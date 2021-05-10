package com.teamdev.booby.compiler.variable;

import com.teamdev.booby.compiler.statement.StatementCompiler;
import com.teamdev.booby.compiler.statement.StatementFiniteStateMachine;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.booby.runtime.InitVariableResultCommand;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.holder.value.BooleanValueHolder;
import com.teamdev.calculator.runtime.holder.value.DoubleValueHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * This is compiler, that processes {@link InitVariableFiniteStateMachine init variable FSM}.
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependencies", "ClassWithTooManyDependencies"})
public class InitVariableCompiler implements ElementCompiler<RuntimeEnvironment> {
    private final Pattern pattern = Pattern.compile("[+-]?[0-9]+(.([0-9]*)?)?");
    private final CompilerFactory<ShuntingYardStack> compilerFactory;
    private final Logger logger = LoggerFactory.getLogger(StatementCompiler.class);

    public InitVariableCompiler(CompilerFactory<ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public Optional<Command<RuntimeEnvironment>> compile(InputCharacterStream stream) {

        logger.info("Start compile Init variable Compiler");
        InitVariableFiniteStateMachine varFiniteStateMachine = new InitVariableFiniteStateMachine(compilerFactory);
        StringBuilder builder = new StringBuilder(10);
        if(varFiniteStateMachine.execute(stream,builder)){
            logger.info("Init variable compiler execute successful");
            String[] components = builder.toString().split("=");
            if(pattern.matcher(components[1]).matches()){
                return Optional.of(new InitVariableResultCommand(components[0], new DoubleValueHolder(Double.parseDouble(components[1]))));
            }
            else return Optional.of(new InitVariableResultCommand(components[0], new BooleanValueHolder(Boolean.valueOf(components[1]))));

        }

        return Optional.empty();
    }
}
