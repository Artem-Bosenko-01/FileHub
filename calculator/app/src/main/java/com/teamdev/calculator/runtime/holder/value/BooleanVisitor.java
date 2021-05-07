package com.teamdev.calculator.runtime.holder.value;

import com.teamdev.calculator.runtime.holder.HolderVisitor;
import com.teamdev.calculator.runtime.holder.ValueHolder;

import java.util.Optional;

@SuppressWarnings({"ClassOnlyUsedInOnePackage", "unused"})
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

    public Optional<Boolean> getBooleanValue(ValueHolder<?> holder){
        BooleanVisitor visitor = new BooleanVisitor();
        holder.accept(visitor);

        if(visitor.isExist()){
            return Optional.of(visitor.value);
        }
        return Optional.empty();
    }
}
