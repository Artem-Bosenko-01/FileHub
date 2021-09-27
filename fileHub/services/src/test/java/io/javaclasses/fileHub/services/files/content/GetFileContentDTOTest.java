package io.javaclasses.fileHub.services.files.content;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.services.files.content.GetFileContentDTO;
import org.junit.jupiter.api.Test;

class GetFileContentDTOTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();
        tester.testConstructor(GetFileContentDTO.class.getConstructor(byte[].class));

    }
}