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

        tester.setDefault(AuthToken.class, new AuthToken("vsdvd")).
                setDefault(FolderId.class, new FolderId("avsa", new UserId("dvsaa"))).
                setDefault(UserId.class, new UserId("dvsaa"));

        tester.testConstructor(GetFolderContentQuery.class.getConstructor(
                AuthToken.class,
                FolderId.class,
                UserId.class));

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();

        tester.testAllPublicInstanceMethods(new GetFolderContentQuery(new AuthToken("sv"),
                new FolderId("sv", new UserId("vsdsvs")), new UserId("vsdsvs")));

    }
}