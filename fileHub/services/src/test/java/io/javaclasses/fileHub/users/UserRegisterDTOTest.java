package io.javaclasses.fileHub.users;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.AuthToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterDTOTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(UserRegisterDTO.class.getConstructor(UserID.class, String.class, String.class, String.class, String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(UserRegisterDTO.class.getMethods());
    }
}