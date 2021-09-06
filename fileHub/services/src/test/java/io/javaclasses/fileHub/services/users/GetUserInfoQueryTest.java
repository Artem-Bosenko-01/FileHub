package io.javaclasses.fileHub.services.users;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.AuthToken;
import io.javaclasses.fileHub.services.ValidationCommandDataException;
import org.junit.jupiter.api.Test;


class GetUserInfoQueryTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(AuthToken.class, new AuthToken("vsdvsv656"));
        tester.testConstructor(GetUserQuery.class.getConstructor(AuthToken.class));

    }

    @Test
    public void checkForNullPointerInSetters() throws ValidationCommandDataException {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new GetUserQuery(new AuthToken("vaas")));

    }
}