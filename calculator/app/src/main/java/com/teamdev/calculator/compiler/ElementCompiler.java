package com.teamdev.calculator.compiler;

import com.teamdev.calculator.runtime.command.Command;

import java.util.Optional;

/**
 *This is compiler, that creates {@link Command command} as a result of execute
 * {@link FiniteStateMachine one of machine}.
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyDependents"})
public interface ElementCompiler<T> {
    Optional<Command<T>> compile(InputCharacterStream stream);
}
