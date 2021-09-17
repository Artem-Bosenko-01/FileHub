package io.javaclasses.fileHub.webservices.user;

import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.services.users.DuplicatedFieldValueException;
import io.javaclasses.fileHub.services.users.RegistrationUserCommand;
import io.javaclasses.fileHub.webservices.UserStorageBaseStub;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public final class RegisterUserNegativeScenariosTestData implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        String baseCredentials = "{\"loginName\":\"ascaacs\",\"password\":\"csdvsvds\"}";

        UserStorageBaseStub userStorageStub = new UserStorageBaseStub();

        return Stream.of(

                Arguments.of(
                        "{}",
                        "{\"message\":\"Request body cannot be empty\"}",
                        400,
                        new RegisterUserBaseStub(userStorageStub)
                ),

                Arguments.of(
                        baseCredentials,
                        "{\"errors\":[{\"field\":\"email\",\"message\":\"User already exist\"}]}",
                        422,
                        new RegistrationProcessThrowDuplicateTokenStub(userStorageStub, "User already exist")
                ),

                Arguments.of(
                        "{\"loginName\":\"ascaacs\",\"password\":\"cs\"}",
                        "{\"errors\":[{\"field\":\"password\",\"message\":\"Password length should be more than 6 symbols\"}]}",
                        422,
                        new RegisterUserBaseStub(userStorageStub)
                ),

                Arguments.of(
                        baseCredentials,
                        "{\"message\":\"Internal server error.\"}",
                        500,
                        new RegistrationProcessThrowNullPointerStub(userStorageStub)
                )

        );
    }

    private static class RegistrationProcessThrowDuplicateTokenStub extends RegisterUserBaseStub {

        private final String message;

        public RegistrationProcessThrowDuplicateTokenStub(UserStorage userStorage, String message) {
            super(userStorage);

            this.message = message;
        }

        @Override
        public Boolean handle(RegistrationUserCommand inputCommand)
                throws DuplicatedFieldValueException {

            throw new DuplicatedFieldValueException("email", message);
        }
    }

    private static class RegistrationProcessThrowNullPointerStub extends RegisterUserBaseStub {

        public RegistrationProcessThrowNullPointerStub(UserStorage userStorage) {
            super(userStorage);
        }

        @Override
        public Boolean handle(RegistrationUserCommand inputCommand) {

            throw new NullPointerException();
        }
    }
}
