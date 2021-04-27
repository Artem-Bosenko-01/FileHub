package com.teamdev.calculator.compiler.fsm;

import com.teamdev.calculator.compiler.InputCharacterStream;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.util.List;

/**
 *This is a mathematical model of computation, that output chain defines by {@link T parameter}.
 * More information <a href="https://wikipedia.org/wiki/Finite-state_machine" title="there"></a>
 * */
public abstract class FiniteStateMachine<T> {

    private final Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory.getLogger(FiniteStateMachine.class);

    public abstract List<State<T>> getStartStates();

    public abstract List<State<T>> getFinishStates();

    public boolean execute(InputCharacterStream inputCharacterStream, T outputChain) {

        logger.info("Start execute fsm");
        List<State<T>> currentPossiblePositions = getStartStates();
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

        if (getFinishStates().contains(currentPosition)) {
            logger.info("Execute was successful");
            return true;
        }

        return currentPosition != null;
    }
}
