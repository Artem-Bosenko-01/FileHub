package com.teamdev.booby.compiler.program;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Semicolon state - is one of states of {@link ProgramFiniteStateMachine}, that defines ';' symbol.
 * */
public class SemicolonState<T> extends State<T>
{
    private final Logger logger = LoggerFactory.getLogger(SemicolonState.class);

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, T builder) {
        logger.info("Start semicolon state transition");
        if(characterStream.getCurrentSymbol() == ';'){
            characterStream.increasePointer();
            logger.info("transition SemicolonState successful");
            return true;
        }
        return false;
    }
}
