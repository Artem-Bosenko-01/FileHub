package com.teamdev.booby.compiler.unaryoperators;

import com.teamdev.booby.runtime.UnaryOperatorFactory;
import com.teamdev.booby.runtime.UnaryOperatorOutputChain;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is one of states for
 * {@link com.teamdev.booby.compiler.unaryoperators.postfix.PostfixOperatorFiniteStateMachine postfix} or
 * {@link com.teamdev.booby.compiler.unaryoperators.prefix.PrefixOperatorFiniteStateMachine}, that defines
 * {@link com.teamdev.booby.runtime.UnaryOperator unary operator} for
 * {@link UnaryOperatorOutputChain  output chain}.
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class UnaryOperatorState extends State<UnaryOperatorOutputChain> {

    private final Logger logger = LoggerFactory.getLogger(UnaryOperatorState.class);
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, UnaryOperatorOutputChain output) {

        UnaryOperatorFactory factory = new UnaryOperatorFactory();
        logger.info("Try transition unary operator state");
        UnaryOperatorFSM fsm = new UnaryOperatorFSM();
        StringBuilder builder = new StringBuilder(10);
        if(fsm.execute(characterStream, builder)){

            if(factory.isOperatorExist(builder.toString())){
                output.setUnaryOperator(builder.toString());
                logger.info("Transition UnaryOperatorState successful");
                return true;
            }
        }
        return false;
    }
}
