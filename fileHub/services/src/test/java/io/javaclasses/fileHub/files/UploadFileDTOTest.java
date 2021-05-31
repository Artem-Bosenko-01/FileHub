package io.javaclasses.fileHub.files;

import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;


class UploadFileDTOTest {

    @Test
    public void checkForNullPointerInConstructor(){
        NullPointerTester tester = new NullPointerTester();
        try {
            tester.testConstructor(UploadFileDTO.class.getConstructor(FileID.class, byte[].class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkForNullPointerInSetters(){
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(UploadFileDTO.class.getMethods());
    }

}