package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Test;

class DeleteFileCommandTest {
    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.testConstructor(DeleteFileCommand.class.getConstructor(AuthToken.class, FileId.class));
    }
}