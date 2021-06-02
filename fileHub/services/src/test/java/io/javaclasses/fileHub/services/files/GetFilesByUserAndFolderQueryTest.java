package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.junit.jupiter.api.Test;

class GetFilesByUserAndFolderQueryTest {
    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();
            tester.testConstructor(GetFilesByUserAndFolderQuery.class.getConstructor(
                    AuthToken.class, FolderId.class, UserId.class));

    }
}