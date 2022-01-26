package io.javaclasses.fileHub.webservices;

import com.google.gson.Gson;

/**
 * Base response that allows converting it to json format.
 */
public class JsonResponse {

    private static final Gson converter = new Gson();

    public String serialize() {

        return converter.toJson(this);
    }
}
