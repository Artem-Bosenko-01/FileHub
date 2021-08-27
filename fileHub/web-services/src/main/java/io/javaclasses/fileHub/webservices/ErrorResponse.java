package io.javaclasses.fileHub.webservices;

import com.google.common.base.Preconditions;

/**
 * Message after executing {@link io.javaclasses.fileHub.services.UserProcess process}
 * that need to be transferred to client side.
 */
public class ErrorResponse extends JsonResponse {

    private final String message;

    public ErrorResponse(String message) {
        this.message = Preconditions.checkNotNull(message);
    }
}
