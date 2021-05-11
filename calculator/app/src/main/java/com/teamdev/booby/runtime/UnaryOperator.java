package com.teamdev.booby.runtime;

/**
 * This is API for basic <a href="https://en.wikipedia.org/wiki/Unary_operation">unary operator</a>.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public interface UnaryOperator {
    double apply(Double operator);
}
