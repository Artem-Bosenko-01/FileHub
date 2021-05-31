package io.javaclasses.fileHub.folders;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.users.tokens.AuthToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteFolderDTOTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(DeleteFolderDTO.class.getConstructor(FolderID.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(DeleteFolderDTO.class.getMethods());
    }
}