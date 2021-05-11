package com.teamdev.booby.runtime;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class UnaryOperatorOutputChain {
    private UnaryOperator unaryOperator;
    private Double operand;
    private final UnaryOperatorFactory factory = new UnaryOperatorFactory();

    public void setUnaryOperator(String unaryOperator) {
        this.unaryOperator = factory.getOperator(unaryOperator);
    }

    public void setOperand(Double operand) {
        this.operand = operand;
    }

    public double execute(){
        return unaryOperator.apply(operand);
    }
}
