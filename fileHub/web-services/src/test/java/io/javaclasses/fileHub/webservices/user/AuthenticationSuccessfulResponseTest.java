package io.javaclasses.fileHub.webservices.user;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.webservices.user.AuthenticationSuccessfulResponse;
import org.junit.jupiter.api.Test;

class AuthenticationSuccessfulResponseTest {
    @Test
    public void checkForNullPointerInConstructor() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicConstructors(AuthenticationSuccessfulResponse.class);

    }
}