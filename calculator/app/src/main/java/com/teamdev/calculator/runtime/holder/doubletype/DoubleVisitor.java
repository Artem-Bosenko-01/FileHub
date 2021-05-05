package com.teamdev.calculator.runtime.holder.doubletype;

import com.teamdev.calculator.runtime.holder.HolderVisitor;
import com.teamdev.calculator.runtime.holder.ValueHolder;

public class DoubleVisitor extends HolderVisitor {

    private Double value;

    public Double getValue() {
        return value;
    }

    @Override
    public void visit(Double value){
        this.setExist();
        this.value = value;
    }

    public Double getDoubleValue(ValueHolder<?> holder){
        DoubleVisitor visitor = new DoubleVisitor();
        holder.accept(visitor);

        if(visitor.isExist()){
            return visitor.getValue();
        }
        return null;
    }

}
