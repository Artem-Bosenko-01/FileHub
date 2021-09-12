package io.javaclasses.fileHub.services.files;

import com.google.common.net.MediaType;
import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import org.junit.jupiter.api.Test;

class UploadFileCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("")).
                setDefault(String.class, "").
                setDefault(MediaType.class, MediaType.GIF).
                setDefault(byte[].class, new byte[]{});

        tester.testConstructor(UploadFileCommand.class.getConstructor(AuthToken.class, String.class,
                MediaType.class, String.class, byte[].class));

    }

    @Test
    public void checkForNullPointerInSetters() throws InvalidValidationCommandDataException {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new UploadFileCommand(new AuthToken(""),
                "", MediaType.PLAIN_TEXT_UTF_8, "", new byte[]{}));

    }

}