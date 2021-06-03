package io.javaclasses.fileHub.services.users;

import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;

class AuthenticationUserCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(String.class, "");

        tester.testConstructor(AuthenticationUserCommand.class.getConstructor(
                String.class, String.class));

    }
}