package com.teamdev.boobby.compiler.fsm;

import com.teamdev.boobby.runtime.ResultScope;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;

public class StatementState extends State<ResultScope> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ResultScope builder) {
        StatementFiniteStateMachine statementMachine = new StatementFiniteStateMachine();
        if(statementMachine.execute(characterStream,builder)){
            return true;
        }
        return false;
    }
}
