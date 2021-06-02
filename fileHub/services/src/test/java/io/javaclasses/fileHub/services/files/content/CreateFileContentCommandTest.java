package io.javaclasses.fileHub.services.files.content;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.persistent.files.FileId;
import org.junit.jupiter.api.Test;

class CreateFileContentCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();
            tester.testConstructor(CreateFileContentCommand.class.getConstructor(
                    AuthToken.class, FileId.class, byte[].class));

    }
}