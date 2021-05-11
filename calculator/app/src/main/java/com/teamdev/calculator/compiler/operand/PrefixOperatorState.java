package com.teamdev.calculator.compiler.operand;

import com.teamdev.booby.compiler.unaryoperators.prefix.PrefixOperatorCompiler;
import com.teamdev.booby.runtime.ProcedureCommand;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;

import java.util.Optional;

public class PrefixOperatorState extends State<ShuntingYardStack> {
    private final ProcedureCommand command;

    public PrefixOperatorState(){command = null;}
    public PrefixOperatorState(ProcedureCommand command) {
        this.command = command;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ShuntingYardStack builder) {

        ProcedureCommand newCom = command;
        Optional<Command<ShuntingYardStack>> command = new PrefixOperatorCompiler(newCom).compile(characterStream);
        if(command.isPresent()){
            command.get().execute(builder);
            return true;
        }

        return false;
    }
}
