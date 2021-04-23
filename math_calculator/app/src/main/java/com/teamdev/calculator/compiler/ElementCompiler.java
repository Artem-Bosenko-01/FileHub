package com.teamdev.calculator.compiler;

import com.teamdev.calculator.runtime.Command;

import java.util.Optional;

public interface ElementCompiler<T> {
    Optional<Command<T>> compile(InputCharacterStream stream);
}
