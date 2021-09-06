package io.javaclasses.fileHub.services.files.content;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class GetFolderContentDTOTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();

        tester.setDefault(FolderId.class, new FolderId("vs", new UserId("vsds"))).
                setDefault(List.class, new ArrayList());

        tester.testConstructor(GetFolderContentDTO.class.getConstructor(List.class, List.class));

    }

    @Test
    public void checkForNullPointerInSetters() {

        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new GetFolderContentDTO(new ArrayList<>(), new ArrayList<>()));
    }
}