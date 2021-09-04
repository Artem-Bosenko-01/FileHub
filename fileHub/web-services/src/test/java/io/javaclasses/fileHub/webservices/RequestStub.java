package io.javaclasses.fileHub.webservices;

import spark.Request;

public class RequestStub extends Request {

    private final String requestBody;

    public RequestStub(String body) {

        requestBody = body;
    }

    @Override
    public String body() {

        return requestBody;
    }
}
