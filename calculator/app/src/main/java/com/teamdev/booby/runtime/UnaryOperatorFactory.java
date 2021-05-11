package com.teamdev.booby.runtime;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class UnaryOperatorFactory {
    private final Map<String, UnaryOperator> operatorMap = new HashMap<>();

    public UnaryOperatorFactory(){
        operatorMap.put("++", operator -> operator + 1);
        operatorMap.put("--", operator -> operator - 1);
    }

    public UnaryOperator getOperator(String str){
        return operatorMap.get(str);
    }
}
