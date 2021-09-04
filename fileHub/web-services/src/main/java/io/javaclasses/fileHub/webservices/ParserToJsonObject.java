package io.javaclasses.fileHub.webservices;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ParserToJsonObject {

    public static JsonObject parse(String requestBody) throws InvalidParsingToJsonObject {

        if (requestBody.isEmpty()) {
            throw new InvalidParsingToJsonObject("Request body cannot be null");
        }


        if (isJsonBodyEmpty(requestBody)) {
            throw new InvalidParsingToJsonObject("Request body cannot be empty");
        }

        return new JsonParser().parse(requestBody).getAsJsonObject();
    }

    private static Boolean isJsonBodyEmpty(String requestLine) {

        String requestBody = requestLine.substring(1, requestLine.length() - 1);

        return requestBody.isEmpty();
    }
}
