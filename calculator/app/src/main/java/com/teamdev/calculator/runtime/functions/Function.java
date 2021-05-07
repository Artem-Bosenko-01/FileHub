package com.teamdev.calculator.runtime.functions;

import com.teamdev.calculator.runtime.command.FunctionCommand;
import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.List;
import java.util.Optional;

/**
 * This is basic type of expression,that implement some of
 * <a href="https://www.mathsisfun.com/sets/functions-common.html">mathematical functions</a>.
 * It is used in {@link FunctionCommand function command}
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyDependents"})
public interface Function {

    Optional<Double> apply(List<ValueHolder<?>> arguments);
}
