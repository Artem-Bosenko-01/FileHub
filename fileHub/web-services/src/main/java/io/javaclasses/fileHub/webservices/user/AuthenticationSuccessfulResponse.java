package io.javaclasses.fileHub.webservices.user;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.webservices.JsonResponse;

/**
 * Serializes token after successful user authentication.
 */
public final class AuthenticationSuccessfulResponse extends JsonResponse {

    private final String token;

    AuthenticationSuccessfulResponse(AuthToken token) {

        this.token = Preconditions.checkNotNull(token.value());
    }
}
