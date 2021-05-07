package com.teamdev.calculator.compiler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *This is a mathematical model of computation, that output chain defines by {@link T parameter}.
 * More information <a href="https://wikipedia.org/wiki/Finite-state_machine" title="there"></a>
 * */
@SuppressWarnings({"ClassWithTooManyTransitiveDependents", "ClassWithTooManyDependents"})
public abstract class FiniteStateMachine<T> {

    private final Logger logger = LoggerFactory.getLogger(FiniteStateMachine.class);

    protected abstract List<State<T>> getStartStates();

    protected abstract List<State<T>> getFinishStates();

    public boolean execute(InputCharacterStream inputCharacterStream, T outputChain) {

        logger.info("Start execute fsm");
        List<State<T>> currentPossiblePositions = getStartStates();
        State<T> currentPosition = null;
        boolean hasBeenTransited;
        int beginPointer = inputCharacterStream.getCurrentPointer();

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

        inputCharacterStream.setPointer(beginPointer);
        return false;
    }
}
