package com.teamdev.booby.runtime.procedure;

import com.teamdev.calculator.runtime.Function;

import java.util.List;
import java.util.Optional;

public class Println extends Function {
    public Println() {
        super("println");
    }

    @Override
    public Optional<Double> apply(List<Double> arguments) {
        for (Double arg: arguments) {
            System.out.println(arg.toString());
        }
        return Optional.empty();
    }
}
