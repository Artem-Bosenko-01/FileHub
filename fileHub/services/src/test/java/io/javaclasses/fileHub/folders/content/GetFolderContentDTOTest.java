package io.javaclasses.fileHub.folders.content;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.folders.FolderID;
import org.junit.jupiter.api.Test;

import java.util.List;


class GetFolderContentDTOTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(GetFolderContentDTO.class.getConstructor(FolderID.class,
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