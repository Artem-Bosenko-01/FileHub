package io.javaclasses.fileHub.services;

import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;

class AuthenticatedUserCommandTest {
    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();
        tester.testConstructor(AuthenticatedUserCommand.class.getConstructor(AuthToken.class));

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(AuthenticatedUserCommand.class.getMethods());

    }
}