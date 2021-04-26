package com.teamdev.calculator.functional;

import java.util.Arrays;
import java.util.function.Predicate;

public class Predicates {


    public static Acceptor loop(Acceptor acceptor){
        return (inputStream, outputChain) -> {
            boolean result = false;
            while (acceptor.execute(inputStream, outputChain)){
                result = true;
            }
            return result;
        };
    }

    public static Acceptor characterAcceptor(Predicate<Character> predicate){
        return (inputStream, outputChain) -> {
            if(!inputStream.isEmpty() && predicate.test(inputStream.getCurrentSymbol())){
                outputChain.append(inputStream.getCurrentSymbol());
                inputStream.increasePointer();
                return true;
            }
            return false;
        };
    }

    public static Acceptor character(char c){
        return characterAcceptor(character -> character==c);
    }

    public static Acceptor chain(Acceptor... acceptors){
        return (inputStream, outputChain) -> Arrays.stream(acceptors).
                allMatch(acceptor -> acceptor.execute(inputStream, outputChain));
    }

    public static Acceptor oneOf(Acceptor... acceptors){
        return (inputStream, outputChain) -> Arrays.stream(acceptors).anyMatch(acceptor -> acceptor.execute(inputStream, outputChain));
    }
    public static Acceptor optional(Acceptor acceptor){
        return (inputStream, outputChain) -> {
            acceptor.execute(inputStream, outputChain);
            return true;
        };
    }
}
