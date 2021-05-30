package io.javaclasses.fileHub.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import io.javaclasses.fileHub.folders.FolderID;
import io.javaclasses.fileHub.users.UserID;
import org.junit.jupiter.api.Test;

class FileSystemUserAndFolderBrowsingQueryTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(FileSystemUserAndFolderBrowsingQuery.class.getConstructor(
                    AuthToken.class, FolderID.class, UserID.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}