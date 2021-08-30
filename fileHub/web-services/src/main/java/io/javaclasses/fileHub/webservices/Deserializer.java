package io.javaclasses.fileHub.webservices;

import com.google.gson.Gson;
import io.javaclasses.fileHub.services.Command;

public class Deserializer<Result extends Command> {

    private static final Gson converter = new Gson();

    public Result deserialize(String requestBody, Class<Result> resultClass) throws InvalidInputDataForDeserializing {

        if (requestBody.isEmpty()) {
            throw new InvalidInputDataForDeserializing("Request body cannot be null");
        }


        if (isJsonBodyEmpty(requestBody)) {
            throw new InvalidInputDataForDeserializing("Request body cannot be empty");
        }

        return converter.fromJson(requestBody, resultClass);
    }

    private Boolean isJsonBodyEmpty(String requestLine) {

        String requestBody = requestLine.substring(1, requestLine.length() - 1);

        return requestBody.isEmpty();
    }
}
