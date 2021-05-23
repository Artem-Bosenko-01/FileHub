package io.javaclasses.fileHub.users;


import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.files.File;
import io.javaclasses.fileHub.files.FileID;
import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(User.class.getConstructor(UserID.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(File.class);
    }
}