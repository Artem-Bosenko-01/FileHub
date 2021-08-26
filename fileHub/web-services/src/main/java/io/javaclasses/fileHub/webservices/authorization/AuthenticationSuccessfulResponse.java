package io.javaclasses.fileHub.webservices.authorization;

import com.google.common.base.Preconditions;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.webservices.JsonResponse;

public class AuthenticationSuccessfulResponse extends JsonResponse {

    private final String token;

    public AuthenticationSuccessfulResponse(AuthToken token) {
        this.token = Preconditions.checkNotNull(token.value());
    }
}
