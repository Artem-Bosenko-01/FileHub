package io.javaclasses.fileHub.services.files;

import com.google.common.net.MediaType;
import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.files.MimeType;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Test;

class UploadFileCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("")).
                setDefault(String.class, "").
                setDefault(MimeType.class, MimeType.TEXT).
                setDefault(UserId.class, new UserId("")).
                setDefault(FolderId.class, new FolderId("v", new UserId(""))).
                setDefault(byte[].class, new byte[]{});

        tester.testConstructor(UploadFileCommand.class.getConstructor(AuthToken.class, String.class,
                MediaType.class, UserId.class, FolderId.class, byte[].class));

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new UploadFileCommand(new AuthToken(""),
                "", MediaType.PLAIN_TEXT_UTF_8, new UserId(""), new FolderId("", new UserId("")),
                new byte[]{}));

    }

}