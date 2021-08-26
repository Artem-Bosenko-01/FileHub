package io.javaclasses.fileHub.webservices;

public class ResponseError extends JsonResponse {

    private final String message;

    public ResponseError(String message) {
        this.message = message;
    }
}
