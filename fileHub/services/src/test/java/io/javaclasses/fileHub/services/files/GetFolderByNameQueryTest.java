package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.files.GetFolderByNameQuery;
import org.junit.jupiter.api.Test;

class GetFolderByNameQueryTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken(""))
                .setDefault(String.class, "")
                .setDefault(UserId.class, new UserId(""));

        tester.testAllPublicConstructors(GetFolderByNameQuery.class);

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new GetFolderByNameQuery(new AuthToken(""),"", new UserId("")));

    }
}