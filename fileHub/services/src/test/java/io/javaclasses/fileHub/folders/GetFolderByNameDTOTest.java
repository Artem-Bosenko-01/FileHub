package io.javaclasses.fileHub.folders;

import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetFolderByNameDTOTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(GetFolderByNameDTO.class.getConstructor(FolderID.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(GetFolderByNameDTO.class.getMethods());
    }
}