package io.javaclasses.fileHub.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Test;

class DeleteFileCommandTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(DeleteFileCommand.class.getConstructor(AuthToken.class, FileID.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}