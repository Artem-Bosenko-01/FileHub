package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Test;

class UpdateFolderCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("")).
                setDefault(FolderId.class, new FolderId("", new UserId(""))).
                setDefault(String.class, "").
                setDefault(UserId.class, new UserId(""));

        tester.testConstructor(UpdateFolderCommand.class.getConstructor(AuthToken.class, FolderId.class,
                String.class, UserId.class, FolderId.class));

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new UpdateFolderCommand(new AuthToken(""),
                new FolderId("", new UserId("")), "", new UserId(""), null));

    }
}