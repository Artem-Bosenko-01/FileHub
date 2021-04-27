package com.teamdev.boobby.runtime;

import java.util.HashMap;
import java.util.Map;

public class ResultScope {

    private final Map<String, Double> resultMap = new HashMap<>();

    public void addParameter(String name, double value){
        resultMap.put(name,value);
    }
    public double getValue(String name){
        return resultMap.get(name);
    }
}
