package com.teamdev.calculator;

import java.util.Optional;

/**
 * This is API for calculator application.
 * */
public interface Calculator {
    Optional<Double> calculate(String inputChain);
}
