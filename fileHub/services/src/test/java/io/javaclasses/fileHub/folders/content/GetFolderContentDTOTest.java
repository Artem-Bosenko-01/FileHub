package io.javaclasses.fileHub.folders.content;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.folders.FolderID;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;


class GetFolderContentDTOTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(GetFolderContentDTO.class.getConstructor(Optional.of(FolderID.class).getClass(),
                    List.class , List.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(GetFolderContentDTO.class.getMethods());
    }
}