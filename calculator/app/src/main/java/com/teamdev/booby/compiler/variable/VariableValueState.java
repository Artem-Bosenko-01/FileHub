package com.teamdev.booby.compiler.variable;

import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is one of states for {@link InitVariableFiniteStateMachine init variable FSM}, that
 * detects value of variable.
 */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class VariableValueState extends State<StringBuilder> {
    private final CompilerFactory<ShuntingYardStack> compilerFactory;
    private final Logger logger = LoggerFactory.getLogger(VariableValueState.class);

    public VariableValueState(CompilerFactory<ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public boolean tryTransition(InputCharacterStream characterStream, StringBuilder builder) {
        logger.info("Try transition for variable value state");
        VariableValueFiniteSTateMachine machine = new VariableValueFiniteSTateMachine(compilerFactory);
        ShuntingYardStack stack = new ShuntingYardStack();
        if(machine.execute(characterStream, stack)){
            builder.append(stack.calculate().getValue());
            logger.info("Transition VariableValueState successful");
            return true;
        }
        return false;
    }
}
