package io.javaclasses.fileHub.services.files.content;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.FolderId;
import org.junit.jupiter.api.Test;

import java.util.List;


class GetFolderContentDTOTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();
        tester.testConstructor(GetFolderContentDTO.class.getConstructor(FolderId.class,
                List.class, List.class));

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(GetFolderContentDTO.class.getMethods());
    }
}