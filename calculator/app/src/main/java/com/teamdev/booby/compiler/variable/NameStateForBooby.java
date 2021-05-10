package com.teamdev.booby.compiler.variable;

import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.function.NameFiniteStateMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is one of states for {@link InitVariableFiniteStateMachine init variable FSM}, that
 * detects name of variable.
 */
public class NameStateForBooby extends State<StringBuilder> {
    private final Logger logger = LoggerFactory.getLogger(NameStateForBooby.class);

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder outputChain) {
        logger.info("Try transition for name state in booby compiler");
        NameFiniteStateMachine nameFiniteStateMachine = new NameFiniteStateMachine();
        if(nameFiniteStateMachine.execute(characterStream, outputChain)){
            logger.info("Transition NameStateForBooby successful");
            return true;
        }
        return false;
    }
}
