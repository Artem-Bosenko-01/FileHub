package io.javaclasses.fileHub.services.files.content;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.FileId;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Test;

class GetFileContentQueryTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("")).
                setDefault(String.class, "");

        tester.testAllPublicConstructors(GetFileContentQuery.class);

    }
}