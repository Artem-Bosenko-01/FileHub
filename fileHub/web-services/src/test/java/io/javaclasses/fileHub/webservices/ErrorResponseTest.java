package io.javaclasses.fileHub.webservices;

import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;

class ErrorResponseTest {
    @Test
    public void checkForNullPointerInConstructor() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicConstructors(ErrorResponse.class);

    }
}