package io.javaclasses.fileHub.folders;

import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;

import java.util.Optional;


class UpdateFolderDTOTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(UpdateFolderDTO.class.getConstructor(FolderID.class, String.class, Optional.of(FolderID.class).getClass()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(UpdateFolderDTO.class.getMethods());
    }
}