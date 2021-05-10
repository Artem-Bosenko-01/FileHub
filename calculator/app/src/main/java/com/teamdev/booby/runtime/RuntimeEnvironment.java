package com.teamdev.booby.runtime;

import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyDependents"})
public class RuntimeEnvironment{
    private final Map<String, ValueHolder<?>> variables;

    private static RuntimeEnvironment instance = null;

    private RuntimeEnvironment(){
        variables = new HashMap<>();
    }

    public static RuntimeEnvironment getInstance() {
        if (instance == null) instance = new RuntimeEnvironment();

        return instance;
    }

    public void setVariable(String name, ValueHolder<?> value){
        variables.put(name,value);
    }

    public ValueHolder<?> getValue(String symbol){
        return variables.get(symbol);
    }

    private Map<String, ValueHolder<?>> getVariables() {
        return Collections.unmodifiableMap(variables);
    }

}
