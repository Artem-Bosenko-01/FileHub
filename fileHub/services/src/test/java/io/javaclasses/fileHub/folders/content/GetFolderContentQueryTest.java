package io.javaclasses.fileHub.folders.content;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.folders.CreateFolderCommand;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetFolderContentQueryTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(GetFolderContentQuery.class.getConstructor(AuthToken.class, FolderID.class, UserID.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(GetFolderContentQuery.class.getMethods());
    }
}