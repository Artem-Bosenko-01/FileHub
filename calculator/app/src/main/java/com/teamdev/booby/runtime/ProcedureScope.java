package com.teamdev.booby.runtime;

import java.util.List;

public class ProcedureScope {

    private String name;
    List<Character> arguments;

    public ProcedureScope(){

    }
    public ProcedureScope(String name, List<Character> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }

    public List<Character> getArguments() {
        return arguments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArguments(List<Character> arguments) {
        this.arguments = arguments;
    }
}
