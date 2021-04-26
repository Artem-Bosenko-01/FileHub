package com.teamdev.calculator.functional;

import com.teamdev.calculator.compiler.InputCharacterStream;

import java.util.function.Consumer;

public interface Acceptor {
    boolean execute(InputCharacterStream inputStream, StringBuilder outputChain);

    default Acceptor withConsumer(Consumer<String> consumer) {
        return (inputStream, outputChain) -> {
            boolean result = execute(inputStream, outputChain);
            if (result) {
                consumer.accept(outputChain.toString());
                outputChain.setLength(0);
            }
            return result;
        };
    }
}

