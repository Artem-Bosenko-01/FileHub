package io.javaclasses.fileHub.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.AnonymousUserCommand;
import io.javaclasses.fileHub.AuthToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemBrowsingQueryTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(FileSystemBrowsingQuery.class.getConstructor(AuthToken.class, FileID.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}