package com.teamdev.calculator.runtime.command;

/**
 *  This is basic API for commands, that are generated after compiling by
 *  {@link com.teamdev.calculator.compiler.ElementCompiler element compilers}.
 *  Type of output for command is defined by {@link T parameter}
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyDependents"})
public interface Command<T> {
    void execute(T stack);
}
