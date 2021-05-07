package com.teamdev.calculator.runtime.holder.value;

import com.teamdev.calculator.runtime.holder.HolderVisitor;
import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.Optional;

@SuppressWarnings("ClassWithTooManyTransitiveDependents")
public class DoubleVisitor extends HolderVisitor {

    private Double value;

    private Double getValue() {
        return value;
    }

    @Override
    public void visit(Double value){
        this.setExist();
        this.value = value;
    }

    public Optional<Double> getDoubleValue(ValueHolder<?> holder){
        DoubleVisitor visitor = new DoubleVisitor();
        holder.accept(visitor);

        if(visitor.isExist()){
            return Optional.of(visitor.getValue());
        }
        return Optional.empty();
    }

}
