package io.javaclasses.fileHub.folders;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteFolderCommandTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(DeleteFolderCommand.class.getConstructor(AuthToken.class, FolderID.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(DeleteFolderCommand.class.getMethods());
    }
}