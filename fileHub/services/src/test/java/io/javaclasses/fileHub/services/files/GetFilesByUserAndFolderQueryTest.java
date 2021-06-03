package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Test;

class GetFilesByUserAndFolderQueryTest {
    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("vdvs"))
                .setDefault(FolderId.class, new FolderId("vsdvs", new UserId("csdvs")))
                .setDefault(UserId.class, new UserId("csdvs"));

        tester.testAllPublicConstructors(GetFilesByUserAndFolderQuery.class);

    }
}