package com.teamdev.booby.compiler.variable;

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

import java.util.Optional;
import java.util.regex.Pattern;

@SuppressWarnings({"ClassWithTooManyTransitiveDependencies", "ClassWithTooManyDependencies"})
public class InitVariableCompiler implements ElementCompiler<RuntimeEnvironment> {
    private final Pattern pattern = Pattern.compile("[0-9]+(.[0-9][0-9]?)?");
    private final CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory;

    public InitVariableCompiler(CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public Optional<Command<RuntimeEnvironment>> compile(InputCharacterStream stream) {
        InitVariableFiniteStateMachine varFiniteStateMachine = new InitVariableFiniteStateMachine(compilerFactory);
        StringBuilder builder = new StringBuilder(10);
        if(varFiniteStateMachine.execute(stream,builder)){
            String[] components = builder.toString().split("=");
            if(pattern.matcher(components[1]).matches()){
                return Optional.of(new InitVariableResultCommand(components[0], new DoubleValueHolder(Double.parseDouble(components[1]))));
            }
            else return Optional.of(new InitVariableResultCommand(components[0], new BooleanValueHolder(Boolean.valueOf(components[1]))));

        }

        return Optional.empty();
    }
}
