package io.javaclasses.fileHub.persistent.files;

import com.google.common.testing.NullPointerTester;
import io.javaclasses.fileHub.persistent.users.UserId;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    public void checkForNullPointerInConstructor() throws NoSuchMethodException {
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicConstructors(File.class);

    }

    @Test
    public void checkForNullPointerInSetters() {
        NullPointerTester tester = new NullPointerTester();
        tester.testAllPublicInstanceMethods(new File(
                new FileId("s",
                        new UserId("vd"),
                        new FolderId("fvd", new UserId("vd"))
                )
        ));
    }
}