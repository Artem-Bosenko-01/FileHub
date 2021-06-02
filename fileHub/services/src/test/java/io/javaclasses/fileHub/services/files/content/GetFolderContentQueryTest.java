package io.javaclasses.fileHub.services.files.content;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Test;

class GetFolderContentQueryTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.testConstructor(GetFolderContentQuery.class.getConstructor(
                AuthToken.class,
                FolderId.class,
                UserId.class));

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();

        tester.testAllPublicInstanceMethods(GetFolderContentQuery.class.getMethods());

    }
}