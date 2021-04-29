package com.teamdev.booby.compiler.fsm;

import com.teamdev.booby.runtime.ResultScope;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.compiler.fsm.exception.InvalidSymbolException;
import com.teamdev.calculator.compiler.fsm.exception.NotExistPairBracketException;

public class StatementState extends State<ResultScope> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ResultScope builder) {
        StatementFiniteStateMachine statementMachine = new StatementFiniteStateMachine();
        try {
            if(statementMachine.execute(characterStream,builder)){
                return true;
            }
        } catch (InvalidSymbolException e) {
            e.printStackTrace();
        } catch (NotExistPairBracketException e) {
            e.printStackTrace();
        }
        return false;
    }
}
