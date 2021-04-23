package com.teamdev.calculator.compiler.fsm;

import com.teamdev.calculator.compiler.InputCharacterStream;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.LinkedList;
import java.util.List;

public class FiniteStateMachine<T> {

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(FiniteStateMachine.class);
    private List<State<T>> startStates;
    private List<State<T>> finishStates = new LinkedList<>();

    protected final void setStartStates(List<State<T>> inputStartStates) {
        this.startStates = inputStartStates;
    }

    protected final void setFinishStates(List<State<T>> finishStates) {
        this.finishStates = finishStates;
    }

    public boolean execute(InputCharacterStream inputCharacterStream, T outputChain) {

        logger.info("Start execute fsm");
        List<State<T>> currentPossiblePositions = startStates;
        State<T> currentPosition = null;
        boolean hasBeenTransited;

        do{
            hasBeenTransited = false;

            for (State<T> state: currentPossiblePositions) {
                if(state.tryTransition(inputCharacterStream, outputChain)){
                    currentPossiblePositions = state.getTransitions();
                    currentPosition = state;
                    hasBeenTransited = true;
                    break;
                }
            }

        }while (hasBeenTransited && !inputCharacterStream.isEmpty());

        if (finishStates.contains(currentPosition)) {
            logger.info("Execute was successful");
            return true;
        }

        return currentPosition != null;
    }
}
