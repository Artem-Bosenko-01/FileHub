package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.files.FolderId;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.junit.jupiter.api.Test;

class GetFileListItemTypeDtoTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();
        tester.setDefault(Integer.class, 0);
        tester.setDefault(String.class, "");
        tester.setDefault(FolderId.class, new FolderId("", new UserId("")));

        tester.testConstructor(FileSystemItemDto.class.getConstructor(String.class, String.class, Integer.class, FolderId.class));


    }
}