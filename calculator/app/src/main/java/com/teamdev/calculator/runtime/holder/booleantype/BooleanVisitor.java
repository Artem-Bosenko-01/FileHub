package com.teamdev.calculator.runtime.holder.booleantype;

import com.teamdev.calculator.runtime.holder.HolderVisitor;
import com.teamdev.calculator.runtime.holder.ValueHolder;

public class BooleanVisitor extends HolderVisitor {

    private boolean value;

    public boolean getValue() {
        return value;
    }

    @Override
    public void visit(Boolean value){
        this.setExist();
        this.value = value;
    }

    public Boolean getBooleanValue(ValueHolder<?> holder){
        BooleanVisitor visitor = new BooleanVisitor();
        holder.accept(visitor);

        if(visitor.isExist()){
            return visitor.value;
        }
        return null;
    }
}
