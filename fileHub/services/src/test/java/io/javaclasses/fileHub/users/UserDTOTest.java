package io.javaclasses.fileHub.users;

import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;

class UserDTOTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(UserDTO.class.getConstructor(UserID.class, String.class, String.class, String.class, String.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(UserDTO.class.getMethods());
    }
}