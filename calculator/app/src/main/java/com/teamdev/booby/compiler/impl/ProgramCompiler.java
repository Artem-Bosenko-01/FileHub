package com.teamdev.booby.compiler.impl;

import com.teamdev.booby.runtime.ResultScope;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.Command;

import java.util.Optional;

public class ProgramCompiler implements ElementCompiler<ResultScope> {
    @Override
    public Optional<Command<ResultScope>> compile(InputCharacterStream stream) {
        return Optional.empty();
    }
}
