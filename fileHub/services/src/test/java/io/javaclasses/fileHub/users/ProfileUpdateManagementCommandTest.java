package io.javaclasses.fileHub.users;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.AuthToken;
import org.junit.jupiter.api.Test;


class ProfileUpdateManagementCommandTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(ProfileUpdateManagementCommand.class.getConstructor(AuthToken.class, UserID.class,
                    String.class, String.class, String.class, String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(ProfileUpdateManagementCommand.class.getMethods());
    }
}