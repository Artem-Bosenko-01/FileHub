package io.javaclasses.fileHub.webservices;

import com.google.gson.Gson;

import java.util.HashMap;


/**
 * Result after sending and processing  {@link org.eclipse.jetty.client.HttpRequest request}.
 * Contains body of received response and {@link javax.servlet.http.HttpServletResponse status code}.
 */
public class TestResponse {

    public final String body;

    public final int status;

    public TestResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }

    public HashMap json() {
        return new Gson().fromJson(body, HashMap.class);
    }

    @Override
    public String toString() {
        return body;
    }
}
