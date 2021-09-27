package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import org.junit.jupiter.api.Test;

class UpdateFolderCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("")).
                setDefault(Long.class, 0L).
                setDefault(String.class, "folder");

        tester.testConstructor(UpdateFolderCommand.class.getConstructor(AuthToken.class, String.class,
                String.class, Long.class, String.class));

    }

    @Test
    public void checkForNullPointerInSetters() throws InvalidValidationCommandDataException {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new UpdateFolderCommand(new AuthToken(""), "",
                "folder", 0L, ""));

    }
}