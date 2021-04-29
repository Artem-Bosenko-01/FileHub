package com.teamdev.calculator.compiler;

import com.teamdev.calculator.runtime.Command;

import java.util.Optional;

/**
 *This is compiler, that creates {@link Command command} as a result of execute
 * {@link com.teamdev.calculator.compiler.fsm.FiniteStateMachine one of machine}
 * */
public interface ElementCompiler<T> {
    Optional<Command<T>> compile(InputCharacterStream stream);
}
