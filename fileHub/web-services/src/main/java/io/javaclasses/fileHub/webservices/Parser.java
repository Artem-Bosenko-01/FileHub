package io.javaclasses.fileHub.webservices;

import com.google.gson.JsonObject;

public class Parser {

    public static JsonObject parse(String requestBody) throws InvalidParsingToJsonObject {

        if (requestBody.isEmpty()) {
            throw new InvalidParsingToJsonObject("Request body cannot be null");
        }


        if (isJsonBodyEmpty(requestBody)) {
            throw new InvalidParsingToJsonObject("Request body cannot be empty");
        }

        return new com.google.gson.JsonParser().parse(requestBody).getAsJsonObject();
    }

    private static Boolean isJsonBodyEmpty(String requestLine) {

        String requestBody = requestLine.substring(1, requestLine.length() - 1);

        return requestBody.isEmpty();
    }
}
