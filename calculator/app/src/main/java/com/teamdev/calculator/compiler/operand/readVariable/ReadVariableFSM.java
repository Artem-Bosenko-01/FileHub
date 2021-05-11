package com.teamdev.calculator.compiler.operand.readVariable;

import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;

import java.util.Arrays;
import java.util.List;

public class ReadVariableFSM extends FiniteStateMachine<ShuntingYardStack> {

    private final VariableState variableState = new VariableState();
    private final PostfixState postfixState = new PostfixState();
    private final PrefixState prefixState = new PrefixState();

    @Override
    protected List<State<ShuntingYardStack>> getStartStates() {
        return Arrays.asList(postfixState,prefixState, variableState);
    }

    @Override
    protected List<State<ShuntingYardStack>> getFinishStates() {
        return Arrays.asList(prefixState,postfixState,variableState);
    }
}
