package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.AuthToken;

import java.util.UUID;

/**
 * Service to generate a unique authentication user token.
 */
final class GenerateUniqueUserToken {

    private GenerateUniqueUserToken() {
    }

    static AuthToken generateToken(AuthorizationStorage storage) {
        while (true) {

            AuthToken token = new AuthToken(UUID.randomUUID().toString());

            if (storage.isTokenExist(token.value())) {

                continue;
            }

            return token;
        }
    }
}
