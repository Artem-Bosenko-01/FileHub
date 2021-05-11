package com.teamdev.booby.runtime;

import java.util.HashMap;
import java.util.Map;

/**
 * This is container, that stores implementation of
 * {@link com.teamdev.booby.compiler.unaryoperators.UnaryOperatorFSM unary operators}.
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class UnaryOperatorFactory {
    private final Map<String, UnaryOperator> operatorMap = new HashMap<>();

    public UnaryOperatorFactory(){
        operatorMap.put("++", operator -> operator + 1);
        operatorMap.put("--", operator -> operator - 1);
    }

    public boolean isOperatorExist(String operator){
        return operatorMap.containsKey(operator);
    }
    public UnaryOperator getOperator(String str){
        return operatorMap.get(str);
    }
}
