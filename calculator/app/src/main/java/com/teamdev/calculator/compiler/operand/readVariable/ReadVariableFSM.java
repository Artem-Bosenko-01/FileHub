package com.teamdev.calculator.compiler.operand.readVariable;

import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * This is machine, that read variable value vy name, also with {@link com.teamdev.booby.runtime.UnaryOperator unary operator}.
 * */
public class ReadVariableFSM extends FiniteStateMachine<ShuntingYardStack> {
    private final Logger logger = LoggerFactory.getLogger(ReadVariableFSM.class);
    private final VariableState variableState = new VariableState();
    private final PostfixState postfixState = new PostfixState();
    private final PrefixState prefixState = new PrefixState();

    public ReadVariableFSM(){
        logger.info("Start Read Variable FSM");
    }

    @Override
    protected List<State<ShuntingYardStack>> getStartStates() {
        return Arrays.asList(postfixState,prefixState, variableState);
    }

    @Override
    protected List<State<ShuntingYardStack>> getFinishStates() {
        return Arrays.asList(prefixState,postfixState,variableState);
    }
}
