package io.javaclasses.fileHub.services.users;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.ValidationCommandDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegisterUserCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(String.class, "");

        tester.testConstructor(RegistrationUserCommand.class.getConstructor(
                String.class, String.class));

    }

    @Test
    public void checkForNullPointerInSetters() throws ValidationCommandDataException {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new RegistrationUserCommand("email@la", "123456"));

    }

    @Test
    public void shouldThrowsValidationCommandDataExceptionAfterParsingInvalidUsersCredentials() {

        String email = "ema";
        String password = "  ";

        Assertions.assertThrows(ValidationCommandDataException.class,
                () -> new AuthenticationUserCommand(email, password));

    }

}