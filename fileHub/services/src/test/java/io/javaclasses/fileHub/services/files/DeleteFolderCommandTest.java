package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.files.DeleteFolderCommand;
import org.junit.jupiter.api.Test;

class DeleteFolderCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();
        tester.testConstructor(DeleteFolderCommand.class.getConstructor(AuthToken.class, FolderId.class));

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(DeleteFolderCommand.class.getMethods());

    }
}