package com.teamdev.booby.compiler.fsm.statement;

import com.teamdev.booby.BoobyCompilerFactoryImpl;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.booby.compiler.TypeBoobyExpression;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.fsm.State;
import com.teamdev.calculator.runtime.Command;

import java.util.Optional;

public class ProcedureState extends State<RuntimeEnvironment> {
    @Override
    public boolean tryTransition(InputCharacterStream characterStream, RuntimeEnvironment builder) {
        Optional<Command<RuntimeEnvironment>> command = new BoobyCompilerFactoryImpl()
                .create(TypeBoobyExpression.PROCEDURE)
                .compile(characterStream);

        if(command.isPresent()){
            command.get().execute(builder);
            return true;
        }
        return false;
    }
}
