package io.javaclasses.fileHub.services.users;

import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;

class AuthenticationUserCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();
        tester.testConstructor(AuthenticationUserCommand.class.getConstructor(
                String.class, String.class));

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(AuthenticationUserCommand.class.getMethods());

    }
}