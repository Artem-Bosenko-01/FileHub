package io.javaclasses.fileHub.folders;

import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;


class CreateFolderDTOTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(CreateFolderDTO.class.getConstructor(String.class, FolderID.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(CreateFolderDTO.class.getMethods());
    }
}