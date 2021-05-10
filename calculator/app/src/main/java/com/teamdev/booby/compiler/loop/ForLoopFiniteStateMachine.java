package com.teamdev.booby.compiler.loop;

import com.teamdev.booby.compiler.program.SemicolonState;
import com.teamdev.booby.runtime.ForOutputChain;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.FiniteStateMachine;
import com.teamdev.calculator.compiler.State;
import com.teamdev.calculator.compiler.operand.CloseBracketState;
import com.teamdev.calculator.compiler.operand.OpenBracketState;
import com.teamdev.calculator.impl.CompilerFactoryImpl;
import com.teamdev.calculator.runtime.ShuntingYardStack;

import java.util.Collections;
import java.util.List;

public class ForLoopFiniteStateMachine extends FiniteStateMachine<ForOutputChain> {

    private final LoopNameState nameState = new LoopNameState();
    private final OpenBracketState<ForOutputChain> openBracketState = new OpenBracketState<>();
    private final InitVariableLoopState initVariableState;
    private final SemicolonState<ForOutputChain> semicolonState = new SemicolonState<>();
    private final BooleanConditionState booleanExpressionState = new BooleanConditionState(new CompilerFactoryImpl());
    private final VariableActionLoopState iterationVariableState;
    private final CloseBracketState<ForOutputChain> closeBracketState = new CloseBracketState<>();
    private final OpenFigureBracketState openFigureBracket = new OpenFigureBracketState();
    private final CodeBlockState codeBlockState = new CodeBlockState();
    private final CloseFigureBracketState closeFigureBracketState = new CloseFigureBracketState();

    public ForLoopFiniteStateMachine(CompilerFactory<ShuntingYardStack> compilerFactory) {
        initVariableState = new InitVariableLoopState(compilerFactory);
        iterationVariableState = new VariableActionLoopState(compilerFactory);

        nameState.addTransition(openBracketState);
        openBracketState.addTransition(initVariableState);
        initVariableState.addTransition(semicolonState);
        semicolonState.addTransition(booleanExpressionState);
        booleanExpressionState.addTransition(semicolonState);
        semicolonState.addTransition(iterationVariableState);
        iterationVariableState.addTransition(closeBracketState);
        closeBracketState.addTransition(openFigureBracket);
        openFigureBracket.addTransition(codeBlockState);
        codeBlockState.addTransition(closeBracketState);
    }

    @Override
    protected List<State<ForOutputChain>> getStartStates() {
        return Collections.singletonList(nameState);
    }

    @Override
    protected List<State<ForOutputChain>> getFinishStates() {
        return Collections.singletonList(closeFigureBracketState);
    }
}
