package com.teamdev.booby.compiler.loop;

import com.teamdev.booby.runtime.ForOutputChain;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.booleanexpression.BooleanExpressionCompiler;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;

import java.util.Optional;

public class BooleanConditionState extends State<ForOutputChain> {
    private final CompilerFactory<ShuntingYardStack> compilerFactory;

    public BooleanConditionState(CompilerFactory<ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, ForOutputChain outputChain) {

        Optional<Command<ShuntingYardStack>> command = new BooleanExpressionCompiler(compilerFactory)
                .compile(characterStream);

        if(command.isPresent()){
            outputChain.setLoopCondition(command.get());
            return true;
        }
        return false;
    }
}
