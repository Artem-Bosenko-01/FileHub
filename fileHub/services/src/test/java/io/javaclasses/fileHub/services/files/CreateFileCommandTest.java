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
            tester.testConstructor(CreateFileCommand.class.getConstructor(AuthToken.class, String.class,
                    MimeType.class, UserId.class, FolderId.class));

    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new CreateFileCommand(new AuthToken(""),
                "",
                MimeType.GIF,
                new UserId(),
                new FolderId())
        );
    }
}