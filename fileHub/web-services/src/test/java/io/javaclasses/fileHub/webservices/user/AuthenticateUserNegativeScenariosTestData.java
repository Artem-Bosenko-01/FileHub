package io.javaclasses.fileHub.webservices.user;

import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.persistent.users.tokens.AuthorizationStorage;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.users.AuthenticationUserCommand;
import io.javaclasses.fileHub.services.users.DuplicatedFieldValueException;
import io.javaclasses.fileHub.services.users.UserNotFoundException;
import io.javaclasses.fileHub.webservices.AuthenticationStorageBaseStub;
import io.javaclasses.fileHub.webservices.UserStorageBaseStub;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public final class AuthenticateUserNegativeScenariosTestData implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {

        String baseCredentials = "{\"loginName\":\"ascaacs\",\"password\":\"csdvsvds\"}";

        UserStorageBaseStub userStorageStub = new UserStorageBaseStub();

        AuthenticationStorageBaseStub authenticationStorageStub = new AuthenticationStorageBaseStub();

        return Stream.of(

                Arguments.of(
                        "{}",
                        "Request body cannot be empty",
                        400,
                        new AuthenticateUserBaseStub(userStorageStub, authenticationStorageStub)
                ),

                Arguments.of(
                        baseCredentials,
                        "Cannot find user by login: ascaacs",
                        404,
                        new AuthenticateNotRegisterUserStub(userStorageStub, authenticationStorageStub)
                ),

                Arguments.of(
                        baseCredentials,
                        "Duplicate token",
                        409,
                        new AuthenticationProcessThrowDuplicateTokenStub(userStorageStub, authenticationStorageStub, "Duplicate token")
                ),

                Arguments.of(
                        "{\"loginName\":\"ascaacs\",\"password\":\"cs\"}",
                        "Error: Invalid user credentials.",
                        422,
                        new AuthenticateUserBaseStub(userStorageStub, authenticationStorageStub)
                ),

                Arguments.of(
                        baseCredentials,
                        "Internal server error.",
                        500,
                        new AuthenticationProcessThrowNullPointerStub(userStorageStub, authenticationStorageStub)
                )

        );
    }

    private static class AuthenticateNotRegisterUserStub extends AuthenticateUserBaseStub {

        public AuthenticateNotRegisterUserStub(UserStorage userStorage,
                                               AuthorizationStorage authorizationStorage) {
            super(userStorage, authorizationStorage);
        }

        @Override
        public AuthToken handle(AuthenticationUserCommand inputCommand)
                throws UserNotFoundException {

            throw new UserNotFoundException(inputCommand.loginName());
        }
    }

    private static class AuthenticationProcessThrowDuplicateTokenStub extends AuthenticateUserBaseStub {

        private final String message;

        public AuthenticationProcessThrowDuplicateTokenStub(UserStorage userStorage,
                                                            AuthorizationStorage authorizationStorage, String message) {
            super(userStorage, authorizationStorage);

            this.message = message;
        }

        @Override
        public AuthToken handle(AuthenticationUserCommand inputCommand)
                throws DuplicatedFieldValueException {

            throw new DuplicatedFieldValueException("token", message);
        }
    }

    private static class AuthenticationProcessThrowNullPointerStub extends AuthenticateUserBaseStub {

        public AuthenticationProcessThrowNullPointerStub(UserStorage userStorage,
                                                         AuthorizationStorage authorizationStorage) {
            super(userStorage, authorizationStorage);
        }

        @Override
        public AuthToken handle(AuthenticationUserCommand inputCommand) {

            throw new NullPointerException();
        }
    }
}
