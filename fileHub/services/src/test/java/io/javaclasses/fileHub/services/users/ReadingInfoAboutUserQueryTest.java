package io.javaclasses.fileHub.services.users;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Test;


class ReadingInfoAboutUserQueryTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("vsdvsv656")).
                setDefault(UserId.class, new UserId("s"));

        tester.testConstructor(ReadUserProfileQuery.class.getConstructor(AuthToken.class, UserId.class));

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new ReadUserProfileQuery(new AuthToken("vaas"), new UserId("")));

    }
}