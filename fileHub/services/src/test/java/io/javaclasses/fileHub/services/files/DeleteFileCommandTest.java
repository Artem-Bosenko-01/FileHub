package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Test;

class DeleteFileCommandTest {
    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("")).
                setDefault(String.class, "");

        tester.testConstructor(DeleteFileCommand.class.getConstructor(AuthToken.class, String.class));
    }
}