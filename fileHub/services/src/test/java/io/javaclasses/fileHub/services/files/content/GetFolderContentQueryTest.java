package io.javaclasses.fileHub.services.files.content;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.ValidationCommandDataException;
import org.junit.jupiter.api.Test;

class GetFolderContentQueryTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("vsdvd")).
                setDefault(String.class, "");

        tester.testConstructor(GetFolderContentQuery.class.getConstructor(AuthToken.class, String.class));

    }

    @Test
    public void checkForNullPointerInSetters() throws ValidationCommandDataException {

        NullPointerTester tester = new NullPointerTester();

        tester.testAllPublicInstanceMethods(new GetFolderContentQuery(new AuthToken("sv"), ""));

    }
}