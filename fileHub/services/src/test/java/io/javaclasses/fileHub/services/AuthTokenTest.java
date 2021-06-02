package io.javaclasses.fileHub.services;

import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;


class AuthTokenTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();
        tester.testConstructor(AuthToken.class.getConstructor(String.class));

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(AuthToken.class.getMethods());

    }
}