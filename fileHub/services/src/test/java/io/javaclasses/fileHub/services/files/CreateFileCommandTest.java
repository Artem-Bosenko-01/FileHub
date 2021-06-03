package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.MimeType;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.junit.jupiter.api.Test;

class CreateFileCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();
        tester.setDefault(AuthToken.class, new AuthToken("svdsv"));
        tester.setDefault(String.class,"");
        tester.setDefault(MimeType.class, MimeType.TEXT);
        tester.setDefault(UserId.class, new UserId("cascs"));
        tester.setDefault(FolderId.class, new FolderId("acasc", new UserId("cascs")));
        tester.testAllPublicConstructors(CreateFileCommand.class);

    }

    @Test
    public void checkForNullPointerInSetters() {
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new CreateFileCommand(
                new AuthToken(""),
                "",
                MimeType.GIF,
                new UserId("buy"),
                new FolderId("avac", new UserId("")))
        );
    }
}