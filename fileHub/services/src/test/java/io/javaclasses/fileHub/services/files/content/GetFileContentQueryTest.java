package io.javaclasses.fileHub.services.files.content;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.persistent.files.FileId;
import org.junit.jupiter.api.Test;

class GetFileContentQueryTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("")).setDefault(FileId.class,
                new FileId("", new UserId("dfb"), new FolderId("", new UserId(""))));

        tester.testAllPublicConstructors(GetFileContentQuery.class);

    }
}