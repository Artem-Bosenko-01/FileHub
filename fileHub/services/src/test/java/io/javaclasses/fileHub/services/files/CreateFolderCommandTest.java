package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.InvalidValidationCommandDataException;
import org.junit.jupiter.api.Test;


class
CreateFolderCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("ac")).
                setDefault(String.class, "acs").
                setDefault(Integer.class, 0);

        tester.testConstructor(CreateFolderCommand.class.getConstructor(AuthToken.class, String.class,
                Integer.class, String.class));

    }
}