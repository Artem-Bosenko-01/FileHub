package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.files.CreateFolderCommand;
import org.junit.jupiter.api.Test;


class
CreateFolderCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("ac")).
                setDefault(String.class, "").
                setDefault(UserId.class, new UserId("vds")).
                setDefault(FolderId.class, new FolderId("vs", new UserId("ssvs")));

        tester.testConstructor(CreateFolderCommand.class.getConstructor(AuthToken.class, String.class, UserId.class,
                FolderId.class));

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new CreateFolderCommand(new AuthToken(""),
                "cadva", new UserId("sv"), null));

    }
}