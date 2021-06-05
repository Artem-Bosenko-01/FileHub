package io.javaclasses.fileHub.services.users;

import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;
import io.javaclasses.fileHub.services.AuthToken;

import java.util.UUID;

public final class GenerateUniqueUserToken {

    public static AuthToken generateToken(AuthorizationStorage storage){

        AuthToken token = new AuthToken(UUID.randomUUID().toString());

        if(storage.isTokenExist(token.value())) return generateToken(storage);
        return token;
    }
}
