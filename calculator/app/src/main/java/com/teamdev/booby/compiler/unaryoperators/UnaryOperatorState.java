package com.teamdev.booby.compiler.unaryoperators;

import com.teamdev.booby.runtime.UnaryOperatorOutputChain;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class UnaryOperatorState extends State<UnaryOperatorOutputChain> {

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, UnaryOperatorOutputChain output) {
        UnaryOperatorFSM fsm = new UnaryOperatorFSM();
        StringBuilder builder = new StringBuilder(10);
        if(fsm.execute(characterStream, builder)){
            output.setUnaryOperator(builder.toString());
            return true;
        }
        return false;
    }
}
