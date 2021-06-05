package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.persistent.files.MimeType;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;


class UpdateFileCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("")).
                setDefault(FolderId.class, new FolderId("aca", new UserId(""))).
                setDefault(String.class, "").
                setDefault(FileId.class, new FileId("", new UserId(""), new FolderId("", new UserId("")))).
                setDefault(MimeType.class, MimeType.TEXT).
                setDefault(Integer.class, Integer.valueOf("0")).
                setDefault(UserId.class, new UserId(""));

        tester.testConstructor(UpdateFileCommand.class.getConstructor(
                AuthToken.class, FileId.class, String.class, MimeType.class, Integer.class,
                UserId.class, FolderId.class
        ));

    }
}