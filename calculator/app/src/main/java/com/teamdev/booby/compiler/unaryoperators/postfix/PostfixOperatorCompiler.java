package com.teamdev.booby.compiler.unaryoperators.postfix;

import com.teamdev.booby.runtime.ProcedureCommand;
import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.booby.runtime.UnaryOperatorOutputChain;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.holder.value.DoubleValueHolder;

import java.util.Optional;

public class PostfixOperatorCompiler implements ElementCompiler<ShuntingYardStack> {

    private ProcedureCommand command = null;

    public PostfixOperatorCompiler(){}

    public PostfixOperatorCompiler(ProcedureCommand command) {
        this.command = command;
    }

    @Override
    public Optional<Command<ShuntingYardStack>> compile(InputCharacterStream stream) {
        PostfixOperatorFiniteStateMachine machine = new PostfixOperatorFiniteStateMachine();
        UnaryOperatorOutputChain unaryOperatorOutputChain = new UnaryOperatorOutputChain();
        RuntimeEnvironment runtimeEnvironment = RuntimeEnvironment.getInstance();

        if(machine.execute(stream,unaryOperatorOutputChain)){
            return Optional.of(new Command<ShuntingYardStack>() {
                @Override
                public void execute(ShuntingYardStack stack) {
                    if(command!=null){
                        command.execute(runtimeEnvironment);
                    }
                    stack.pushOperand(new DoubleValueHolder(unaryOperatorOutputChain.execute()));

                }
            });
        }
        return Optional.empty();
    }
}
