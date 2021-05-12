package com.teamdev.booby.compiler.unaryoperators;

import com.teamdev.booby.compiler.statement.ProcedureState;
import com.teamdev.booby.compiler.statement.StatementFiniteStateMachine;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * This is one of states for {@link UnaryOperatorFSM unary operator FSM}, that defines and put into builder
 *  symbol as '+' or '-'.
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class DoubleOperatorSymbolState extends State<StringBuilder> {

    private final Pattern pattern = Pattern.compile("[+-]");
    private final Logger logger = LoggerFactory.getLogger(DoubleOperatorSymbolState.class);

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        logger.info("Try transition for double operator state unary operator fsm");
        if(pattern.matcher(String.valueOf(characterStream.getCurrentSymbol())).matches()){
            builder.append(characterStream.getCurrentSymbol());
            characterStream.increasePointer();
            logger.info("Transition DoubleOperatorSymbolState successful");
            return true;
        }
        return false;
    }
}
