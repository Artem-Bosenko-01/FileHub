package io.javaclasses.fileHub.folders;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.users.UserID;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Test;

import java.util.Optional;


class CreateFolderCommandTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(CreateFolderCommand.class.getConstructor(AuthToken.class, String.class, UserID.class,
                    FolderID.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(CreateFolderCommand.class.getMethods());
    }
}