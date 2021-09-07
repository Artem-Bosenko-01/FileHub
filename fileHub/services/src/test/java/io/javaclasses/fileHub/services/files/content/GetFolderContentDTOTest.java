package io.javaclasses.fileHub.services.files.content;

import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class GetFolderContentDTOTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.testConstructor(GetFolderContentDTO.class.getConstructor(List.class));

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new GetFolderContentDTO(new ArrayList<>()));
    }
}