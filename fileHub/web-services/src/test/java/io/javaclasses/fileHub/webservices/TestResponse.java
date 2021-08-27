package io.javaclasses.fileHub.webservices;

import com.google.gson.Gson;

import java.util.HashMap;

public class TestResponse {
    public final String body;
    public final int status;

    public TestResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }

    public HashMap<String, String> json() {
        return new Gson().fromJson(body, HashMap.class);
    }
}
