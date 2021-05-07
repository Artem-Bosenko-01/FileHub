package com.teamdev.booby.compiler.variable;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;

@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class VariableValueState extends State<StringBuilder> {
    private final CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory;

    public VariableValueState(CompilerFactory<TypeOfExpressionElement, ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        VariableValueFiniteSTateMachine machine = new VariableValueFiniteSTateMachine(compilerFactory);
        ShuntingYardStack stack = new ShuntingYardStack();
        if(machine.execute(characterStream, stack)){
            builder.append(stack.calculate().getValue());
            return true;
        }


        return false;
    }
}
