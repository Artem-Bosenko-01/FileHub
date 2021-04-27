package com.teamdev.boobby;

import com.teamdev.calculator.compiler.InputCharacterStream;

public class BoobyImpl implements Booby{
    @Override
    public void execute(String program, RuntimeEnvironment environment) {
        InputCharacterStream stream = new InputCharacterStream(program);
    }
}
