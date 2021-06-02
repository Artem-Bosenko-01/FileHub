package io.javaclasses.fileHub.services.users;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.users.UserId;
import io.javaclasses.fileHub.services.AuthToken;
import org.junit.jupiter.api.Test;


class ProfileUpdateManagementCommandTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();
        tester.testConstructor(UpdatingProfileCommand.class.getConstructor(AuthToken.class, UserId.class,
                String.class, String.class, String.class, String.class));

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(UpdatingProfileCommand.class.getMethods());

    }
}