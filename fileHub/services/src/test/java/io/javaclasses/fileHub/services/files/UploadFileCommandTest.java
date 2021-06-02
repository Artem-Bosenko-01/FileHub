package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.MimeType;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Test;

class UploadFileCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

            tester.testConstructor(UploadFileCommand.class.getConstructor(AuthToken.class, String.class,
                    MimeType.class, UserId.class, FolderId.class, byte[].class));

    }

    @Test
    public void checkForNullPointerInSetters(){

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(UploadFileCommand.class.getMethods());

    }

}