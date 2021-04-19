package com.teamdev.calculator.fsm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Scanner{
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String read(){
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Invalid read text");
    }


    public static void output(String str){
        System.out.println(str);
    }
}
