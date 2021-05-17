package com.teamdev.calculator.runtime.functions;

import com.teamdev.booby.runtime.Println;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * This is factory, that stores a variety of possible {@link Function functions}.
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyDependencies"})
final class FunctionFactory {

    private final Map<String, Function> functionMap = new HashMap<>();

    FunctionFactory(StringBuilder writer){

        functionMap.put("avg", new AverageFunction());
        functionMap.put("max", new MaxFunction());
        functionMap.put("min", new MinFunction());
        functionMap.put("log10", new LgFunction());
        functionMap.put("log", new LogFunction());
        functionMap.put("sin", new SinFunction());
        functionMap.put("cos", new CosFunction());
        functionMap.put("pi", new PiFunc());
        functionMap.put("sum", new SumFunction());
        functionMap.put("println", new Println(writer));
    }

    public Function getFunction(String name){
        return functionMap.get(name);
    }
}
