package arithmetic_calculator;

import com.teamdev.calculator.base.InputCharacterStream;
import com.teamdev.calculator.base.finite_state_machine.FiniteStateMachine;
import com.teamdev.calculator.base.state.State;

import java.util.List;

public class NumberFiniteStateMachineTest extends FiniteStateMachine {

    protected NumberFiniteStateMachineTest(List<State> startStates) {
        super(startStates);
    }

    @Override
    public boolean execute(InputCharacterStream inputCharacterStream, StringBuilder outputChain) {

        return false;
    }
}
