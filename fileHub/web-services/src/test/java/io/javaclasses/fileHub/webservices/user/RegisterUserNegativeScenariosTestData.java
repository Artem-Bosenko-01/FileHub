package io.javaclasses.fileHub.webservices.user;

import io.javaclasses.fileHub.persistent.files.FolderStorage;
import io.javaclasses.fileHub.persistent.users.UserStorage;
import io.javaclasses.fileHub.services.users.DuplicatedFieldValueException;
import io.javaclasses.fileHub.services.users.RegistrationUserCommand;
import io.javaclasses.fileHub.webservices.UserStorageBaseStub;
import io.javaclasses.fileHub.webservices.files.FolderStorageBaseStub;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public final class RegisterUserNegativeScenariosTestData implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        String baseCredentials = "{\"loginName\":\"ascaacs\",\"password\":\"csdvsvds\"}";

        UserStorageBaseStub userStorageStub = new UserStorageBaseStub();
        FolderStorageBaseStub folderStorageBaseStub = new FolderStorageBaseStub();

        return Stream.of(

                Arguments.of(
                        "{}",
                        "{\"message\":\"Request body cannot be empty\"}",
                        400,
                        new RegisterUserBaseStub(userStorageStub, folderStorageBaseStub)
                ),

                Arguments.of(
                        baseCredentials,
                        "{\"errors\":[{\"field\":\"email\",\"message\":\"User already exist\"}]}",
                        422,
                        new RegistrationProcessThrowDuplicateTokenStub(userStorageStub, folderStorageBaseStub,
                                "User already exist")
                ),

                Arguments.of(
                        "{\"loginName\":\"ascaacs\",\"password\":\"cs\"}",
                        "{\"errors\":[{\"field\":\"password\",\"message\":\"Password length should be more than 6 symbols\"}]}",
                        422,
                        new RegisterUserBaseStub(userStorageStub, folderStorageBaseStub)
                ),

                Arguments.of(
                        baseCredentials,
                        "{\"message\":\"Internal server error.\"}",
                        500,
                        new RegistrationProcessThrowNullPointerStub(userStorageStub, folderStorageBaseStub)
                )

        );
    }

    private static class RegistrationProcessThrowDuplicateTokenStub extends RegisterUserBaseStub {

        private final String message;

        public RegistrationProcessThrowDuplicateTokenStub(UserStorage userStorage, FolderStorage folderStorage, String message) {
            super(userStorage, folderStorage);

            this.message = message;
        }

        @Override
        public Boolean handle(RegistrationUserCommand inputCommand)
                throws DuplicatedFieldValueException {

            throw new DuplicatedFieldValueException("email", message);
        }
    }

    private static class RegistrationProcessThrowNullPointerStub extends RegisterUserBaseStub {

        public RegistrationProcessThrowNullPointerStub(UserStorage userStorage, FolderStorage folderStorage) {
            super(userStorage, folderStorage);
        }

        @Override
        public Boolean handle(RegistrationUserCommand inputCommand) {

            throw new NullPointerException();
        }
    }
}
