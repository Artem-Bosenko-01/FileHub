package com.teamdev.booby.runtime;

/**
 * This is result of processing
 * {@link com.teamdev.booby.compiler.unaryoperators.prefix.PrefixOperatorCompiler prefix operator compiler} or
 * {@link com.teamdev.booby.compiler.unaryoperators.postfix.PostfixOperatorCompiler postfix operator compiler}.
 * */
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
