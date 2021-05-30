package io.javaclasses.fileHub.users;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Test;


class ProfileUpdateManagementCommandTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(ProfileUpdateCommand.class.getConstructor(AuthToken.class, UserID.class,
                    String.class, String.class, String.class, String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(ProfileUpdateCommand.class.getMethods());
    }
}