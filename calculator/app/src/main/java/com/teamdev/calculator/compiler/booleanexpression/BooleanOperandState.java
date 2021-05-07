package com.teamdev.calculator.compiler.booleanexpression;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.State;

import com.teamdev.calculator.compiler.operand.OperandCompiler;
import com.teamdev.calculator.runtime.operators.BooleanScope;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.ShuntingYardStack;

import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class BooleanOperandState extends State<BooleanScope> {
    private final CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory;

    public BooleanOperandState(CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, BooleanScope builder) {
        ElementCompiler<ShuntingYardStack> compiler = new OperandCompiler(compilerFactory);
        Optional<Command<ShuntingYardStack>> command = compiler.compile(characterStream);
        ShuntingYardStack stack = new ShuntingYardStack();
        if(command.isPresent()){
            command.get().execute(stack);
            builder.addOperand((Double) stack.calculate().getValue());
            return true;
        }
        return false;
    }
}
