package com.teamdev.calculator.runtime.holder;

public class HolderVisitor {
    private boolean isExist = false;

    public boolean isExist() {
        return isExist;
    }

    public void setExist() {
        isExist = true;
    }

    public void visit(Double visit){}

    public void visit(Boolean visit){}
}
