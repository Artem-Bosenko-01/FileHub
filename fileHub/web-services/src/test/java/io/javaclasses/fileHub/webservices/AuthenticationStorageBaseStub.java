package io.javaclasses.fileHub.webservices;

import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationUsers;
import io.javaclasses.fileHub.persistent.users.tokens.UserAuthToken;

import java.time.ZonedDateTime;
import java.util.Optional;

public class AuthenticationStorageBaseStub implements AuthorizationStorage {

    @Override
    public void create(AuthorizationUsers inputDataObject) {
    }

    @Override
    public void update(AuthorizationUsers inputDataObject) {
    }

    @Override
    public void delete(String dataRecordID) {
    }

    @Override
    public Optional<AuthorizationUsers> findByID(UserAuthToken dataRecordID) {

        return Optional.of(
                new AuthorizationUsers(
                        new UserAuthToken("token"),
                        new UserId("id"),
                        ZonedDateTime.now().plusHours(6)));
    }

    @Override
    public boolean isTokenExist(String token) {
        return false;
    }
}

