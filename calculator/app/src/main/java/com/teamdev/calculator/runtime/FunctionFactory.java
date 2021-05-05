package com.teamdev.calculator.runtime;

import com.teamdev.booby.runtime.procedure.Println;
import com.teamdev.calculator.runtime.functions.*;

import java.util.HashMap;
import java.util.Map;

/**
 * This is factory, that stores a variety of possible {@link Function functions}
 * */
public final class FunctionFactory {

    private final Map<String, Function> functionMap = new HashMap<>();

    public FunctionFactory(){
        AverageFunction absFunction = new AverageFunction();
        MaxFunction maxFunction = new MaxFunction();
        MinFunction minFunction = new MinFunction();
        LgFunction lgFunction = new LgFunction();
        LogFunction logFunction = new LogFunction();
        SinFunction sinFunction = new SinFunction();
        CosFunction cosFunction = new CosFunction();
        PiFunc piFunc = new PiFunc();
        SumFunction sumFunction = new SumFunction();
        Println printlnProcedure = new Println();

        functionMap.put("avg",absFunction);
        functionMap.put("max", maxFunction);
        functionMap.put("min", minFunction);
        functionMap.put("log10", lgFunction);
        functionMap.put("log", logFunction);
        functionMap.put("sin", sinFunction);
        functionMap.put("cos", cosFunction);
        functionMap.put("pi", piFunc);
        functionMap.put("sum", sumFunction);
        functionMap.put("println", printlnProcedure);
    }

    public Function getFunction(String name){
        return functionMap.get(name);
    }
}
