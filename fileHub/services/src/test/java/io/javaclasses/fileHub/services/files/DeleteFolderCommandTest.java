package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.files.DeleteFolderCommand;
import org.junit.jupiter.api.Test;

class DeleteFolderCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("")).
                setDefault(FolderId.class, new FolderId("", new UserId("sfvs")));

        tester.testAllPublicConstructors(DeleteFolderCommand.class);

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new DeleteFolderCommand(new AuthToken(""), new FolderId("",
                new UserId(""))));

    }
}