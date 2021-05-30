package io.javaclasses.fileHub.files.content;

import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;

class GetFileContentDTOTest {
    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(GetFileContentDTO.class.getConstructor(byte[].class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}