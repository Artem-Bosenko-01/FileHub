package io.javaclasses.fileHub.services.files;

import com.google.common.testing.NullPointerTester;
import org.junit.jupiter.api.Test;

class GetFileListItemTypeDtoTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {

        NullPointerTester tester = new NullPointerTester();
        tester.setDefault(Long.class, 0L);
        tester.setDefault(String.class, "");
        tester.setDefault(ItemType.class, ItemType.FILE);

        tester.testConstructor(FileSystemItemDto.class.getConstructor(String.class, String.class, Long.class,
                ItemType.class, String.class));


    }
}