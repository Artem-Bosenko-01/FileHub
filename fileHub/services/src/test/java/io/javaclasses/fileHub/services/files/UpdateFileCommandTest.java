package io.javaclasses.fileHub.services.files;

import com.google.common.net.MediaType;
import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Test;


class UpdateFileCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("")).
                setDefault(String.class, "*").
                setDefault(MediaType.class, MediaType.GIF).
                setDefault(Long.class, 0L);

        tester.testConstructor(UpdateFileCommand.class.getConstructor(
                AuthToken.class, String.class, String.class, MediaType.class, Long.class, String.class
        ));

    }
}