package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.MimeType;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.junit.jupiter.api.Test;


class UpdateFileCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();
        tester.testConstructor(UpdateFileCommand.class.getConstructor(
                AuthToken.class, FileId.class, String.class, MimeType.class, Integer.class,
                UserId.class, FolderId.class
        ));

    }
}