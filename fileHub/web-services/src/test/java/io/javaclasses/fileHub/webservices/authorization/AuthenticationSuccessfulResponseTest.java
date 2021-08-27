package io.javaclasses.fileHub.webservices.authorization;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.webservices.authentication.AuthenticationSuccessfulResponse;
import org.junit.jupiter.api.Test;

class AuthenticationSuccessfulResponseTest {
    @Test
    public void checkForNullPointerInConstructor() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicConstructors(AuthenticationSuccessfulResponse.class);

    }
}