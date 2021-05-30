package io.javaclasses.fileHub.files.content;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.files.FileID;
import org.junit.jupiter.api.Test;

class CreateFileContentCommandTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(CreateFileContentCommand.class.getConstructor(
                    AuthToken.class, FileID.class, byte[].class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}