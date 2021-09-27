package io.javaclasses.fileHub.services.users;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthenticateUserCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(String.class, "");

        tester.testConstructor(AuthenticationUserCommand.class.getConstructor(
                String.class, String.class));

    }

    @Test
    public void shouldThrowsValidationCommandDataExceptionAfterParsingInvalidUsersCredentials() {

        String email = "ema";
        String password = "  ";

        Assertions.assertThrows(InvalidValidationCommandDataException.class,
                () -> new AuthenticationUserCommand(email, password));

    }
}