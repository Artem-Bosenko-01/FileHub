package io.javaclasses.fileHub.webservices;

/**
 * Message after executing {@link io.javaclasses.fileHub.services.UserProcess process}
 * that need to be transferred to client side.
 */
public class ErrorResponse extends JsonResponse {

    private final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
