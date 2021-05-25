package io.javaclasses.fileHub.files.content;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.files.FileID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateFileContentDTOTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(CreateFileContentDTO.class.getConstructor(FileID.class, byte[].class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}