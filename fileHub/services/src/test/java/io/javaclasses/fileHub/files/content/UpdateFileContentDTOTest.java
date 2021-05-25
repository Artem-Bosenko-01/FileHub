package io.javaclasses.fileHub.files.content;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.AnonymousUserCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateFileContentDTOTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(UpdateFileContentDTO.class.getConstructor(byte[].class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}