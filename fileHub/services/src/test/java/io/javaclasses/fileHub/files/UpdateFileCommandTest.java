package io.javaclasses.fileHub.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.AuthToken;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;
import org.junit.jupiter.api.Test;


class UpdateFileCommandTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(UpdateFileCommand.class.getConstructor(
                    AuthToken.class, FileID.class, String.class, MimeType.class, Integer.class,
                    UserID.class, FolderID.class
            ));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}