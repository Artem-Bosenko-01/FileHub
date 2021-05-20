package com.teamdev;


import com.teamdev.booby.Booby;
import com.teamdev.booby.impl.BoobyCompilerFactoryImpl;
import com.teamdev.booby.impl.BoobyImpl;
import com.teamdev.booby.runtime.RuntimeEnvironment;

import static spark.Spark.*;

public class ApplicationSpark {

    public static void main(String[] args) {

        staticFiles.location("/");
        staticFiles.externalLocation(System.getProperty("java.io.tmpdir"));

        post("/run", (request, response) -> {
            String input = request.body();

            StringBuilder builder = new StringBuilder();
            Booby calculator = new BoobyImpl(new BoobyCompilerFactoryImpl(builder));
            RuntimeEnvironment environment = RuntimeEnvironment.getInstance();
            calculator.execute(input, environment);
            return builder;
        });
    }
}
