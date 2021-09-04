package io.javaclasses.fileHub.webservices;

import spark.Response;

public class ResponseStub extends Response {

    private int statusCode;

    @Override
    public void status(int statusCode) {

        this.statusCode = statusCode;
    }

    @Override
    public int status() {

        return statusCode;
    }
}
