package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Test;


class
CreateFolderCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("ac")).
                setDefault(String.class, "").
                setDefault(Integer.class, 0);

        tester.testConstructor(CreateFolderCommand.class.getConstructor(AuthToken.class, String.class,
                Integer.class, String.class));

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new CreateFolderCommand(new AuthToken(""),
                "cadva", 9, null));

    }
}