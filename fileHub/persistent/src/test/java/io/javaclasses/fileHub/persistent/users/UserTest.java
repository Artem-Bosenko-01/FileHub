package io.javaclasses.fileHub.persistent.users;


import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {
        NullPointerTester tester = new NullPointerTester();
            tester.testConstructor(User.class.getConstructor(UserId.class));

    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(User.class.getMethods());
    }
}