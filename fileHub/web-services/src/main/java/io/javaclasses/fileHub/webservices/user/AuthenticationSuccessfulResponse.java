package io.javaclasses.fileHub.webservices.user;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.webservices.JsonResponse;

/**
 * Allows serializing token after successful user authentication.
 */
public final class AuthenticationSuccessfulResponse extends JsonResponse {

    private final String token;

    public AuthenticationSuccessfulResponse(AuthToken token) {

        this.token = Preconditions.checkNotNull(token.value());
    }
}
