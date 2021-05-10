package com.teamdev.booby.compiler.procedure;

import com.teamdev.booby.runtime.RuntimeEnvironment;
import com.teamdev.booby.runtime.ProcedureCommand;
import com.teamdev.calculator.compiler.CompilerFactory;
import com.teamdev.calculator.compiler.ElementCompiler;
import com.teamdev.calculator.compiler.InputCharacterStream;
import com.teamdev.calculator.compiler.TypeOfExpressionElement;
import com.teamdev.calculator.compiler.function.FunctionFiniteStateMachine;
import com.teamdev.calculator.compiler.function.LetterState;
import com.teamdev.calculator.runtime.command.Command;
import com.teamdev.calculator.runtime.functions.FunctionScope;
import com.teamdev.calculator.runtime.ShuntingYardStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
/**
 * This is compiler, that execute {@link FunctionFiniteStateMachine function FSM} and return
 * {@link ProcedureCommand procedure}
 * */
@SuppressWarnings("ClassWithTooManyTransitiveDependencies")
public class ProcedureCompiler implements ElementCompiler<RuntimeEnvironment> {

    private final CompilerFactory<ShuntingYardStack> compilerFactory;
    private final Logger logger = LoggerFactory.getLogger(ProcedureCompiler.class);

    public ProcedureCompiler(CompilerFactory<ShuntingYardStack> compilerFactory) {
        this.compilerFactory = compilerFactory;
    }

    @Override
    public Optional<Command<RuntimeEnvironment>> compile(InputCharacterStream stream) {
        logger.info("Start compile Procedure Compiler");
        FunctionFiniteStateMachine machine = new FunctionFiniteStateMachine(compilerFactory);
        FunctionScope scope = new FunctionScope();
        if(machine.execute(stream, scope)){
            logger.info("Procedure compiler execute successful");
            return Optional.of(new ProcedureCommand(scope));
        }

        return Optional.empty();
    }
}
