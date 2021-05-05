package com.teamdev.booby.runtime;

import java.util.HashMap;
import java.util.Map;


public class RuntimeEnvironment implements Cloneable {
    private final Map<String, Double> variables;

    private static RuntimeEnvironment instance;

    private RuntimeEnvironment(){
        variables = new HashMap<>();
    }

    public static RuntimeEnvironment getInstance() {
        if (instance == null) instance = new RuntimeEnvironment();

        return instance;
    }

    public void setVariable(String name, Double value){
        variables.put(name,value);
    }

    public Double getValue(String symbol){
        return variables.get(symbol);
    }

    public Map<String, Double> getVariables() {
        return variables;
    }

    public void clone(RuntimeEnvironment environment){
        variables.putAll(environment.getVariables());
    }

}
